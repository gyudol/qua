package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.ShortsComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ShortsCommentUpdateEvent {

    private String commentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public static ShortsCommentUpdateEvent toDto(ShortsComment shortsComment, LocalDateTime updatedAt) {
        return ShortsCommentUpdateEvent.builder()
                .commentUuid(shortsComment.getCommentUuid())
                .content(shortsComment.getContent())
                .updatedAt(updatedAt)
                .build();
    }
}
