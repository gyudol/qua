package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.ShortsComment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShortsCommentDeleteEvent {

    private String commentUuid;
    private boolean status;

    public static ShortsCommentDeleteEvent toDto(ShortsComment shortsComment) {
        return ShortsCommentDeleteEvent.builder()
                .commentUuid(shortsComment.getCommentUuid())
                .status(shortsComment.isStatus())
                .build();
    }
}
