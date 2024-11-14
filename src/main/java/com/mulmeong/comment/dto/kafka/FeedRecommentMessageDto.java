package com.mulmeong.comment.dto.kafka;

import com.mulmeong.comment.entity.FeedRecomment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedRecommentMessageDto {
    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private String recommentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public static FeedRecommentMessageDto toRecommentDto(FeedRecomment feedRecomment) {
        return FeedRecommentMessageDto.builder()
                .commentUuid(feedRecomment.getCommentUuid())
                .recommentUuid(feedRecomment.getRecommentUuid())
                .memberUuid(feedRecomment.getMemberUuid())
                .content(feedRecomment.getContent())
                .createdAt(feedRecomment.getCreatedAt())
                .updatedAt(feedRecomment.getUpdatedAt())
                .likeCount(0)
                .dislikeCount(0)
                .build();
    }
}
