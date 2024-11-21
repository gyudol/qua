package com.mulmeong.event;

import com.mulmeong.comment.read.entity.FeedComment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class FeedCommentCreateEvent {

    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private boolean status;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;


    public FeedCommentCreateEvent(FeedCommentCreateEvent message) {
        this.feedUuid = message.getFeedUuid();
        this.memberUuid = message.getMemberUuid();
        this.commentUuid = message.getCommentUuid();
        this.status = message.isStatus();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.updatedAt = message.getUpdatedAt();
        this.likeCount = message.getLikeCount();
        this.dislikeCount = message.getDislikeCount();
    }

    public FeedComment toEntity() {
        return FeedComment.builder()
                .feedUuid(feedUuid)
                .memberUuid(memberUuid)
                .commentUuid(commentUuid)
                .content(content)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .likeCount(likeCount)
                .dislikeCount(dislikeCount)
                .recommentCount(0)
                .build();
    }

}
