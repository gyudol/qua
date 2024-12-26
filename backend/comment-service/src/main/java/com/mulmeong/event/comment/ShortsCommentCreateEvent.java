package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.ShortsComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ShortsCommentCreateEvent {

    private String shortsUuid;
    private String memberUuid;
    private String commentUuid;
    private boolean isDeleted;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ShortsCommentCreateEvent toDto(ShortsComment shortsComment) {
        return ShortsCommentCreateEvent.builder()
                .shortsUuid(shortsComment.getShortsUuid())
                .commentUuid(shortsComment.getCommentUuid())
                .memberUuid(shortsComment.getMemberUuid())
                .isDeleted(shortsComment.isDeleted())
                .content(shortsComment.getContent())
                .createdAt(shortsComment.getCreatedAt())
                .updatedAt(shortsComment.getUpdatedAt())
                .build();
    }
}
