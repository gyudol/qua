package com.mulmeong.utility.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DislikeRenewRequestDto {

    private String kind;
    private String kindUuid;
    private Long dislikeCount;

    @Builder
    public DislikeRenewRequestDto(
            String kind,
            String kindUuid,
            Long dislikeCount
    ) {

        this.kind = kind;
        this.kindUuid = kindUuid;
        this.dislikeCount = dislikeCount;
    }
}
