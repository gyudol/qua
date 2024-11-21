package com.mulmeong.member.auth.dto.in;

import com.mulmeong.member.auth.domain.Member;
import com.mulmeong.member.auth.domain.OauthProvider;
import com.mulmeong.member.auth.util.RandomNicknameUtil;
import com.mulmeong.member.auth.vo.in.SignUpAndSignInRequestVo;
import com.mulmeong.member.common.exception.BaseException;
import com.mulmeong.member.common.response.BaseResponseStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignUpAndInRequestDto {

    private String oauthId;
    private String oauthProvider;
    private String email;

    public static SignUpAndInRequestDto toDto(SignUpAndSignInRequestVo requestVo) {

        //oAuth 정보가 유효한지 확인
        if (!OauthProvider.isSupported(requestVo.getOauthProvider())) {
            throw new BaseException(BaseResponseStatus.NO_SUPPORTED_PROVIDER);
        }

        return SignUpAndInRequestDto.builder()
                .oauthId(requestVo.getOauthId())
                .oauthProvider(requestVo.getOauthProvider())
                .email(requestVo.getEmail())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .memberUuid(UUID.randomUUID().toString())
                .oauthId(oauthId)
                .oauthProvider(oauthProvider)
                .email(email)
                .nickname(new RandomNicknameUtil().generateNickname()) // 랜덤 닉네임(경우의수 840억)
                .profileImageUrl("image/198af19d-bbc6-4252-a632-d1a1ccd5c659.webp") // 기본 이미지
                .build();
    }

    @Builder
    public SignUpAndInRequestDto(String oauthId, String oauthProvider, String email) {
        this.oauthId = oauthId;
        this.oauthProvider = oauthProvider;
        this.email = email;
    }
}
