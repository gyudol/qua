package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.FeedComment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedCommentDeleteEvent {

    private String commentUuid;
    private boolean isDeleted;

    public static FeedCommentDeleteEvent toDto(FeedComment feedcomment) {
        return FeedCommentDeleteEvent.builder()
                .commentUuid(feedcomment.getCommentUuid())
                .isDeleted(feedcomment.isDeleted())
                .build();
    }
}
