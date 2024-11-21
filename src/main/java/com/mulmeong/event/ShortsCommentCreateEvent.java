package com.mulmeong.event;

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
    private boolean status;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public static ShortsCommentCreateEvent toDto(ShortsComment shortsComment) {
        return ShortsCommentCreateEvent.builder()
                .shortsUuid(shortsComment.getShortsUuid())
                .commentUuid(shortsComment.getCommentUuid())
                .memberUuid(shortsComment.getMemberUuid())
                .status(shortsComment.isStatus())
                .content(shortsComment.getContent())
                .createdAt(shortsComment.getCreatedAt())
                .updatedAt(shortsComment.getUpdatedAt())
                .likeCount(0)
                .dislikeCount(0)
                .build();
    }
}
