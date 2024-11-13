package com.mulmeong.comment.dto.kafka;

import com.mulmeong.comment.entity.FeedComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FeedCommentMessageDto {
    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private String content;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public static FeedCommentMessageDto toCommentDto(FeedComment feedComment) {
        return FeedCommentMessageDto.builder()
                .feedUuid(feedComment.getFeedUuid())
                .memberUuid(feedComment.getMemberUuid())
                .commentUuid(feedComment.getCommentUuid())
                .status(feedComment.isStatus())
                .content(feedComment.getContent())
                .createdAt(feedComment.getCreatedAt())
                .updatedAt(feedComment.getUpdatedAt())
                .likeCount(0)
                .dislikeCount(0)
                .build();
    }
}
