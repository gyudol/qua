package com.mulmeong.event;

import com.mulmeong.comment.entity.FeedRecomment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedRecommentUpdateEvent {

    private String recommentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public static FeedRecommentUpdateEvent toDto(FeedRecomment feedRecomment, LocalDateTime updatedAt) {
        return FeedRecommentUpdateEvent.builder()
                .recommentUuid(feedRecomment.getRecommentUuid())
                .content(feedRecomment.getContent())
                .updatedAt(updatedAt)
                .build();
    }
}
