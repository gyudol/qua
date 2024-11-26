package com.mulmeong.comment.read.dto.out;

import com.mulmeong.comment.read.entity.FeedComment;
import com.mulmeong.comment.read.vo.FeedCommentResponseVo;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FeedCommentResponseDto {

    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer recommentCount;

    public static FeedCommentResponseDto toDto(FeedComment feedComment) {
        return FeedCommentResponseDto.builder()
                .feedUuid(feedComment.getFeedUuid())
                .memberUuid(feedComment.getMemberUuid())
                .commentUuid(feedComment.getCommentUuid())
                .content(feedComment.getContent())
                .createdAt(feedComment.getCreatedAt())
                .updatedAt(feedComment.getUpdatedAt())
                .likeCount(feedComment.getLikeCount())
                .dislikeCount(feedComment.getDislikeCount())
                .recommentCount(feedComment.getRecommentCount())
                .build();
    }

    public FeedCommentResponseVo toVo() {
        return FeedCommentResponseVo.builder()
                .feedUuid(feedUuid)
                .memberUuid(memberUuid)
                .commentUuid(commentUuid)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .likeCount(likeCount)
                .dislikeCount(dislikeCount)
                .recommentCount(recommentCount)
                .build();
    }
}
