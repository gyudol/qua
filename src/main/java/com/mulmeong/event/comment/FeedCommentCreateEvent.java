package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.FeedComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedCommentCreateEvent {

    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private boolean isDeleted;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FeedCommentCreateEvent toDto(FeedComment feedcomment) {
        return FeedCommentCreateEvent.builder()
                .feedUuid(feedcomment.getFeedUuid())
                .commentUuid(feedcomment.getCommentUuid())
                .memberUuid(feedcomment.getMemberUuid())
                .isDeleted(feedcomment.isDeleted())
                .content(feedcomment.getContent())
                .createdAt(feedcomment.getCreatedAt())
                .updatedAt(feedcomment.getUpdatedAt())
                .build();
    }

}
