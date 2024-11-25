package com.mulmeong.member.auth.application;

import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.member.auth.domain.Member;
import com.mulmeong.member.auth.dto.in.NewAccessTokenRequestDto;
import com.mulmeong.member.auth.dto.in.SignUpAndInRequestDto;
import com.mulmeong.member.auth.dto.out.NewAccessTokenResponseDto;
import com.mulmeong.member.auth.dto.out.SignUpAndInResponseDto;
import com.mulmeong.member.auth.infrastructure.MemberRepository;
import com.mulmeong.member.auth.util.RandomNicknameUtil;
import com.mulmeong.member.common.config.kafka.EventPublisher;
import com.mulmeong.member.common.exception.BaseException;
import com.mulmeong.member.common.jwt.properties.JwtProperties;
import com.mulmeong.member.common.jwt.util.JwtProvider;
import com.mulmeong.member.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;

    private final JwtProperties jwtProperties;
    private final JwtProvider jwtProvider;

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String TOKEN_PREFIX = "refreshToken";

    private final RandomNicknameUtil randomNicknameUtil;
    private final EventPublisher eventPublisher;


    /**
     * 회원가입 및 로그인 겸용(oAtuh only)
     * 이미 회원가입된 경우 바로 토큰 발급, 아닌 경우 회원가입 후 토큰 발급
     * 회원가입시 Kafka 이벤트 발행 => 회원에 대한 ReadDB 생성.
     *
     * @param requestDto 회원가입 요청 DTO
     * @return dto(uuid, 토큰 2종)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SignUpAndInResponseDto signUpAndSignIn(SignUpAndInRequestDto requestDto) {

        // 회원 조회 => 회원이면 바로 토큰 발급
        Member existMember = memberRepository.findByOauthIdAndOauthProvider(
                        requestDto.getOauthId(), requestDto.getOauthProvider())
                .orElse(null);
        if (existMember != null) {
            return respondSignIn(existMember);
        }

        /* 회원이 아닌 경우 => 회원가입, Read-DB 반영 Kafka 이벤트 발행 */
        // entity로 변환하며 uuid, 랜덤 닉네임 할당
        Member signUpMember = memberRepository.save(requestDto.toEntity(randomNicknameUtil));
        eventPublisher.send(MemberCreateEvent.from(signUpMember));

        return respondSignIn(signUpMember);
    }

    /**
     * Refresh Token으로 새로운 Access Token 발급
     * 조회 -> 비교 -> AccessToken, RefreshToken 발급,저장 -> 응답.
     *
     * @param requestDto memberUuid + refreshToken
     * @return 응답 dto(uuid, accessToken, refreshToken)
     */
    @Override
    public NewAccessTokenResponseDto createNewAccessToken(NewAccessTokenRequestDto requestDto) {

        String memberUuid = requestDto.getMemberUuid();

        // Refresh Token 조회해 요청값과 비교 => 일치하지 않으면 예외
        if (!findRefreshTokenByMemberUuid(memberUuid).equals(requestDto.getRefreshToken())) {
            throw new BaseException(BaseResponseStatus.NO_SIGN_IN);
        }

        // 발급 -> 저장(리프레쉬 토큰) -> 응답
        String newAccessToken = jwtProvider.generateToken(memberUuid, jwtProperties.getAccessExpireTime());
        String newRefreshToken = jwtProvider.generateToken(memberUuid, jwtProperties.getRefreshExpireTime());
        saveOrUpdateRefreshToken(memberUuid, requestDto.getRefreshToken());

        return new NewAccessTokenResponseDto(requestDto.getMemberUuid(), newAccessToken, newRefreshToken);
    }

    /**
     * memberUuid로 저장된 Refresh Token 조회하는 private 메서드.
     *
     * @param memberUuid 회원 식별자(Redis Key 조회용)
     * @return 조회된 Refresh Token
     */
    private String findRefreshTokenByMemberUuid(String memberUuid) {

        String key = TOKEN_PREFIX + memberUuid;

        String refreshToken = (String) redisTemplate.opsForValue().get(key);

        if (refreshToken == null) {
            throw new BaseException(BaseResponseStatus.NO_SIGN_IN);
        }
        return refreshToken;
    }

    /**
     * 토큰 생성 후 응답하는 private 메서드.
     *
     * @param member 회원 정보
     * @return 토큰 발급 후 응답 DTO
     */
    private SignUpAndInResponseDto respondSignIn(Member member) {

        // 토큰 발급(로그인)
        String accessToken = jwtProvider.generateToken(member.getMemberUuid(), jwtProperties.getAccessExpireTime());
        String refreshToken = jwtProvider.generateToken(member.getMemberUuid(), jwtProperties.getRefreshExpireTime());

        // Redis에 Refresh Token 저장
        saveOrUpdateRefreshToken(member.getMemberUuid(), refreshToken);

        return SignUpAndInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberUuid(member.getMemberUuid())
                .build();
    }

    /**
     * Refresh Token 저장 또는 업데이트하는 private 메서드.
     *
     * @param memberUuid   회원 식별자(Redis Key)
     * @param refreshToken (새로 발급된) Refresh Token
     */
    private void saveOrUpdateRefreshToken(String memberUuid, String refreshToken) {
        String key = TOKEN_PREFIX + memberUuid;

        redisTemplate.opsForValue().set(key, refreshToken, jwtProperties.getRefreshExpireTime(), TimeUnit.MILLISECONDS);
    }
}
