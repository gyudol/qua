package com.mulmeong.member.auth.dto.out;

import com.mulmeong.member.auth.vo.out.SignUpAndSignInResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpAndInResponseDto {

    private String memberUuid;
    private String accessToken;
    private String refreshToken;
    private boolean isSignUp;

    public SignUpAndSignInResponseVo toVo() {
        return SignUpAndSignInResponseVo.builder()
                .memberUuid(memberUuid)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isSignUp(isSignUp)
                .build();
    }

    @Builder
    public SignUpAndInResponseDto(String memberUuid, String accessToken, String refreshToken, boolean isSignUp) {
        this.memberUuid = memberUuid;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isSignUp = isSignUp;
    }
}
