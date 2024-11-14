package com.mulmeong.comment.dto.kafka;

import com.mulmeong.comment.entity.FeedComment;
import com.mulmeong.comment.entity.FeedRecomment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MessageDto {
    private String contentType;
    private String contentUuid;
    private String memberUuid;
    private String commentUuid;
    private String recommentUuid;
    private boolean status;
    private String content;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public static MessageDto toFeedCommentDto(FeedComment feedComment) {
        return MessageDto.builder()
                .contentType("FEED_COMMENT")
                .contentUuid(feedComment.getFeedUuid())
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

    public static MessageDto toFeedRecommentDto(FeedRecomment feedRecomment) {
        return MessageDto.builder()
                .contentType("FEED_RECOMMENT")
                .memberUuid(feedRecomment.getMemberUuid())
                .commentUuid(feedRecomment.getCommentUuid())
                .recommentUuid(feedRecomment.getRecommentUuid())
                .content(feedRecomment.getContent())
                .createdAt(feedRecomment.getCreatedAt())
                .updatedAt(feedRecomment.getUpdatedAt())
                .likeCount(0)
                .dislikeCount(0)
                .build();
    }
}
