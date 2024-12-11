package com.mulmeong.event.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShortsRecommentDeleteEvent {

    private String memberUuid;
    private String recommentUuid;

    public static ShortsRecommentDeleteEvent toDto(String recommentUuid, String memberUuid) {
        return ShortsRecommentDeleteEvent.builder()
                .memberUuid(memberUuid)
                .recommentUuid(recommentUuid)
                .build();
    }
}
