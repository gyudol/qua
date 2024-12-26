package com.mulmeong.member.auth.dto.in;

import com.mulmeong.member.auth.vo.in.NewAccessTokenRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewAccessTokenRequestDto {

    private String refreshToken;
    private String memberUuid;

    public static NewAccessTokenRequestDto toDto(NewAccessTokenRequestVo requestVo) {
        return NewAccessTokenRequestDto.builder()
                .refreshToken(requestVo.getRefreshToken())
                .memberUuid(requestVo.getMemberUuid())
                .build();
    }

    @Builder
    public NewAccessTokenRequestDto(String refreshToken, String memberUuid) {
        this.refreshToken = refreshToken;
        this.memberUuid = memberUuid;
    }
}
