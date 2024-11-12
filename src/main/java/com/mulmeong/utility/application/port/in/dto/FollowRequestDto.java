package com.mulmeong.utility.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowRequestDto {

    private String sourceUuid;
    private String targetUuid;

    @Builder
    public FollowRequestDto(
            String sourceUuid,
            String targetUuid
    ) {
        this.sourceUuid = sourceUuid;
        this.targetUuid = targetUuid;
    }
}
