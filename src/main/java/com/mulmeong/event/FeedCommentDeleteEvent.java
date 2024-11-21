package com.mulmeong.event;

import com.mulmeong.comment.read.entity.FeedComment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class FeedCommentDeleteEvent {

    private String commentUuid;
    private boolean status;

    public FeedCommentDeleteEvent(FeedCommentDeleteEvent message) {
        this.commentUuid = message.getCommentUuid();
        this.status = message.isStatus();
    }

    public FeedComment toEntity(FeedComment feedComment) {
        return FeedComment.builder()
                .id(feedComment.getId())
                .feedUuid(feedComment.getFeedUuid())
                .memberUuid(feedComment.getMemberUuid())
                .commentUuid(commentUuid)
                .content(feedComment.getContent())
                .status(status)
                .createdAt(feedComment.getCreatedAt())
                .updatedAt(feedComment.getUpdatedAt())
                .likeCount(feedComment.getLikeCount())
                .dislikeCount(feedComment.getDislikeCount())
                .recommentCount(feedComment.getRecommentCount())
                .build();
    }
}
