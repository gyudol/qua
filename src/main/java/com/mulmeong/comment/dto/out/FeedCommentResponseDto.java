package com.mulmeong.comment.dto.out;

import com.mulmeong.comment.entity.FeedComment;
import com.mulmeong.comment.vo.out.FeedCommentResponseVo;
import jakarta.persistence.Column;
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

    public static FeedCommentResponseDto toDto(FeedComment feedComment) {
        return FeedCommentResponseDto.builder()
                .feedUuid(feedComment.getFeedUuid())
                .memberUuid(feedComment.getMemberUuid())
                .commentUuid(feedComment.getCommentUuid())
                .content(feedComment.getContent())
                .createdAt(feedComment.getCreatedAt())
                .updatedAt(feedComment.getUpdatedAt())
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
                .build();
    }
}
