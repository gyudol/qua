package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.ShortsComment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShortsCommentDeleteEvent {
    private String shortUuid;
    private String memberUuid;
    private String commentUuid;
    private boolean isDeleted;

    public static ShortsCommentDeleteEvent toDto(ShortsComment shortsComment) {
        return ShortsCommentDeleteEvent.builder()
                .shortUuid(shortsComment.getShortsUuid())
                .memberUuid(shortsComment.getMemberUuid())
                .commentUuid(shortsComment.getCommentUuid())
                .isDeleted(shortsComment.isDeleted())
                .build();
    }
}
