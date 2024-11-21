package com.mulmeong.comment.dto.out;

import com.mulmeong.comment.entity.ShortsComment;
import com.mulmeong.comment.vo.out.ShortsCommentResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ShortsCommentResponseDto {

    private String shortsUuid;
    private String memberUuid;
    private String commentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;

    public static ShortsCommentResponseDto toDto(ShortsComment shortsComment) {
        return ShortsCommentResponseDto.builder()
                .shortsUuid(shortsComment.getShortsUuid())
                .memberUuid(shortsComment.getMemberUuid())
                .commentUuid(shortsComment.getCommentUuid())
                .content(shortsComment.getContent())
                .createdAt(shortsComment.getCreatedAt())
                .updatedAt(shortsComment.getUpdatedAt())
                .status(shortsComment.isStatus())
                .build();
    }

    public ShortsCommentResponseVo toVo() {
        return ShortsCommentResponseVo.builder()
                .shortsUuid(shortsUuid)
                .memberUuid(memberUuid)
                .commentUuid(commentUuid)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .status(status)
                .build();
    }
}