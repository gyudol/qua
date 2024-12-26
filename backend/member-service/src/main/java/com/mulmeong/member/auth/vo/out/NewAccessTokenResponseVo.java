package com.mulmeong.member.auth.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewAccessTokenResponseVo {

    private String memberUuid;
    private String accessToken;
    private String refreshToken;

    @Builder
    public NewAccessTokenResponseVo(String memberUuid, String accessToken, String refreshToken) {
        this.memberUuid = memberUuid;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
