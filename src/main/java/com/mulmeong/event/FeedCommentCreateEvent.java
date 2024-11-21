package com.mulmeong.event;

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
    private boolean status;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public static FeedCommentCreateEvent toDto(FeedComment feedcomment) {
        return FeedCommentCreateEvent.builder()
                .feedUuid(feedcomment.getFeedUuid())
                .commentUuid(feedcomment.getCommentUuid())
                .memberUuid(feedcomment.getMemberUuid())
                .status(feedcomment.isStatus())
                .content(feedcomment.getContent())
                .createdAt(feedcomment.getCreatedAt())
                .updatedAt(feedcomment.getUpdatedAt())
                .likeCount(0)
                .dislikeCount(0)
                .build();
    }

}
