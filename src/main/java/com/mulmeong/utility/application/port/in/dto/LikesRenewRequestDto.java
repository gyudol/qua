package com.mulmeong.utility.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikesRenewRequestDto {

    private String kind;
    private String kindUuid;
    private Long likeCount;

    @Builder
    public LikesRenewRequestDto(
            String kind,
            String kindUuid,
            Long likeCount
    ) {

        this.kind = kind;
        this.kindUuid = kindUuid;
        this.likeCount = likeCount;
    }
}
