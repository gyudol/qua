package com.mulmeong.member.auth.dto.in;

import com.mulmeong.member.auth.domain.Member;
import com.mulmeong.member.auth.vo.in.SignUpAndSignInRequestVo;
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

    public static SignUpAndInRequestDto toDto(SignUpAndSignInRequestVo signUpAndSignInRequestVo) {

        return SignUpAndInRequestDto.builder()
                .oauthId(signUpAndSignInRequestVo.getOauthId())
                .oauthProvider(signUpAndSignInRequestVo.getOauthProvider())
                .email(signUpAndSignInRequestVo.getEmail())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .memberUuid(UUID.randomUUID().toString())
                .oauthId(oauthId)
                .oauthProvider(oauthProvider)
                .email(email)
                .nickname(UUID.randomUUID().toString().substring(0, 15)) // todo: 형용사 + 명사 + 숫자조합 수정 필요
                .profileImageUrl(null) // todo: 정책 결정 후 수정
                .build();
    }

    @Builder
    public SignUpAndInRequestDto(String oauthId, String oauthProvider, String email) {
        this.oauthId = oauthId;
        this.oauthProvider = oauthProvider;
        this.email = email;
    }
}
