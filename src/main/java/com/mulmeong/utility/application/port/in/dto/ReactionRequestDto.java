package com.mulmeong.utility.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReactionRequestDto {

    private String memberUuid;
    private String kind;
    private String kindUuid;
    private Integer status;

    @Builder
    public ReactionRequestDto(
            String memberUuid,
            String kind,
            String kindUuid,
            Integer status
    ) {
        this.memberUuid = memberUuid;
        this.kind = kind;
        this.kindUuid = kindUuid;
        this.status = status;
    }
}
