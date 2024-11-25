package com.mulmeong.member.auth.dto.out;

import com.mulmeong.member.auth.vo.out.NewAccessTokenResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewAccessTokenResponseDto {

    private String memberUuid;
    private String accessToken;
    private String refreshToken;


    public NewAccessTokenResponseVo toVo() {
        return NewAccessTokenResponseVo.builder()
                .memberUuid(memberUuid)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
