package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.FeedComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedCommentUpdateEvent {

    private String commentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public static FeedCommentUpdateEvent toDto(FeedComment feedcomment, LocalDateTime updatedAt) {
        return FeedCommentUpdateEvent.builder()
                .commentUuid(feedcomment.getCommentUuid())
                .content(feedcomment.getContent())
                .updatedAt(updatedAt)
                .build();
    }
}
