package com.mulmeong.event.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedRecommentDeleteEvent {

    private String memberUuid;
    private String recommentUuid;

    public static FeedRecommentDeleteEvent toDto(String recommentUuid, String memberUuid) {
        return FeedRecommentDeleteEvent.builder()
                .memberUuid(memberUuid)
                .recommentUuid(recommentUuid)
                .build();
    }
}
