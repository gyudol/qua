package com.mulmeong.event;

import com.mulmeong.comment.read.entity.FeedComment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class FeedCommentUpdateEvent {

    private String commentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public FeedCommentUpdateEvent(FeedCommentUpdateEvent message) {
        this.commentUuid = message.getCommentUuid();
        this.content = message.getContent();
        this.updatedAt = message.getUpdatedAt();
    }

    public FeedComment toEntity(FeedComment feedComment) {
        return FeedComment.builder()
                .id(feedComment.getId())
                .feedUuid(feedComment.getFeedUuid())
                .memberUuid(feedComment.getMemberUuid())
                .commentUuid(commentUuid)
                .content(content)
                .status(feedComment.isStatus())
                .createdAt(feedComment.getCreatedAt())
                .updatedAt(updatedAt)
                .likeCount(feedComment.getLikeCount())
                .dislikeCount(feedComment.getDislikeCount())
                .recommentCount(feedComment.getRecommentCount())
                .build();
    }

}
