package com.mulmeong.event.comment;

import com.mulmeong.comment.read.entity.FeedComment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class FeedCommentUpdateEvent {

    private String commentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public FeedComment toEntity(FeedComment feedComment) {
        return FeedComment.builder()
                .id(feedComment.getId())
                .feedUuid(feedComment.getFeedUuid())
                .memberUuid(feedComment.getMemberUuid())
                .commentUuid(commentUuid)
                .content(content)
                .isDeleted(feedComment.isDeleted())
                .createdAt(feedComment.getCreatedAt())
                .updatedAt(updatedAt)
                .likeCount(feedComment.getLikeCount())
                .dislikeCount(feedComment.getDislikeCount())
                .recommentCount(feedComment.getRecommentCount())
                .build();
    }

}
