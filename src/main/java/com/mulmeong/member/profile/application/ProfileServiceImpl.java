package com.mulmeong.member.profile.application;

import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
import com.mulmeong.member.auth.domain.Member;
import com.mulmeong.member.auth.infrastructure.MemberRepository;
import com.mulmeong.member.common.config.kafka.EventPublisher;
import com.mulmeong.member.common.exception.BaseException;
import com.mulmeong.member.common.response.BaseResponseStatus;
import com.mulmeong.member.profile.dto.in.NicknameUpdateRequestDto;
import com.mulmeong.member.profile.dto.in.ProfileImgUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final MemberRepository memberRepository;
    private final EventPublisher eventPublisher;

    /**
     * 닉네임 조회.
     *
     * @param memberUuid 회원 uuid
     * @return 닉네임
     */
    @Override
    public String getNickname(String memberUuid) {
        return memberRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER))
                .getNickname();
    }

    /**
     * 닉네임 수정.
     *
     * @param requestDto uuid, 닉네임
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNickname(NicknameUpdateRequestDto requestDto) {

        Member member = memberRepository.findByMemberUuid(requestDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER));

        memberRepository.save(requestDto.toEntity(member));

        // Kafka 이벤트 발행
        eventPublisher.send(MemberNicknameUpdateEvent.toEvent(requestDto));
    }

    /**
     * 닉네임 중복 검사.
     *
     * @param nickname 중복 검사할 닉네임
     * @return 중복 여부(true: 중복 없음, false: 중복 있음)
     */
    @Override
    public boolean checkNickname(String nickname) {

        return !memberRepository.existsMemberByNickname(nickname);
    }

    /**
     * 프로필 이미지 수정.
     *
     * @param requestDto uuid, 프로필 이미지url.
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProfileImage(ProfileImgUpdateRequestDto requestDto) {

        Member member = memberRepository.findByMemberUuid(requestDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER));

        memberRepository.save(requestDto.toEntity(member));

        // Kafka 이벤트 발행
        eventPublisher.send(MemberProfileImgUpdateEvent.toEvent(requestDto));
    }
}
