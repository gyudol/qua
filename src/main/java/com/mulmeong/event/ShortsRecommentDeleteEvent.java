package com.mulmeong.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShortsRecommentDeleteEvent {

    private String recommentUuid;

    public static ShortsRecommentDeleteEvent toDto(String recommentUuid) {
        return ShortsRecommentDeleteEvent.builder()
                .recommentUuid(recommentUuid)
                .build();
    }
}
