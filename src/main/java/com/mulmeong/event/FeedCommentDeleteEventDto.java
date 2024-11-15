package com.mulmeong.event;

import com.mulmeong.comment.entity.FeedComment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedCommentDeleteEventDto {

    private String commentUuid;
    private boolean status;

    public static FeedCommentDeleteEventDto toDto(FeedComment feedcomment) {
        return FeedCommentDeleteEventDto.builder()
                .commentUuid(feedcomment.getCommentUuid())
                .status(feedcomment.isStatus())
                .build();
    }
}
