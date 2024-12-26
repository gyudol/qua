package com.mulmeong.utility.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikesRequestDto {

    private String memberUuid;
    private String kind;
    private String kindUuid;

    @Builder
    public LikesRequestDto(
            String memberUuid,
            String kind,
            String kindUuid
    ) {
        this.memberUuid = memberUuid;
        this.kind = kind;
        this.kindUuid = kindUuid;
    }
}
