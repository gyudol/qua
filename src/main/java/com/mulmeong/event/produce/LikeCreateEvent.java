package com.mulmeong.event.produce;

import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeCreateEvent {
    private String memberUuid;
    private String kind;
    private String kindUuid;

    public static LikeCreateEvent toDto(LikesRequestDto dto) {
        return LikeCreateEvent.builder()
                .memberUuid(dto.getMemberUuid())
                .kind(dto.getKind())
                .kindUuid(dto.getKindUuid())
                .build();
    }
}