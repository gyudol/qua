package com.mulmeong.member.auth.vo.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpAndSignInResponseVo {

    private String memberUuid;
    private String accessToken;
    private String refreshToken;

    @JsonProperty("isSignUp")
    private boolean isSignUp;

    @Builder
    public SignUpAndSignInResponseVo(String memberUuid, String accessToken, String refreshToken, boolean isSignUp) {
        this.memberUuid = memberUuid;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isSignUp = isSignUp;
    }
}
