package com.mulmeong.event.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedRecommentDeleteEvent {

    private String recommentUuid;

    public static FeedRecommentDeleteEvent toDto(String recommentUuid) {
        return FeedRecommentDeleteEvent.builder()
                .recommentUuid(recommentUuid)
                .build();
    }
}
