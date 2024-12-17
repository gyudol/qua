package com.mulmeong.event.produce;

import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UnfollowEvent {

    private String sourceUuid;
    private String targetUuid;

    public static UnfollowEvent toDto(FollowRequestDto dto) {
        return UnfollowEvent.builder()
                .sourceUuid(dto.getSourceUuid())
                .targetUuid(dto.getTargetUuid())
                .build();
    }
}
