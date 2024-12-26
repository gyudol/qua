package com.mulmeong.event.produce;

import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowCreateEvent {
    private String sourceUuid;
    private String targetUuid;

    public static FollowCreateEvent toDto(FollowRequestDto dto) {
        return FollowCreateEvent.builder()
                .sourceUuid(dto.getSourceUuid())
                .targetUuid(dto.getTargetUuid())
                .build();
    }
}