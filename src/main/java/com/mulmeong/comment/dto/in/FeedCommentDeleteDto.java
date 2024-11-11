package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.FeedComment;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeedCommentDeleteDto {
    public static FeedComment toEntity(FeedComment feedComment) {
        return FeedComment.builder()
                .id(feedComment.getId())
                .memberUuid(feedComment.getMemberUuid())
                .feedUuid(feedComment.getFeedUuid())
                .commentUuid(feedComment.getCommentUuid())
                .content(feedComment.getContent())
                .status(false)
                .build();
    }
}
