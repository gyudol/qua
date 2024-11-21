package com.mulmeong.event;


import com.mulmeong.comment.read.entity.FeedComment;
import com.mulmeong.comment.read.entity.FeedRecomment;
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
public class FeedRecommentCreateEvent {

    private String memberUuid;
    private String commentUuid;
    private String recommentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public FeedRecommentCreateEvent(FeedRecommentCreateEvent message) {
        this.memberUuid = message.getMemberUuid();
        this.commentUuid = message.getCommentUuid();
        this.recommentUuid = message.getRecommentUuid();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.updatedAt = message.getUpdatedAt();
        this.likeCount = message.getLikeCount();
        this.dislikeCount = message.getDislikeCount();
    }

    public FeedRecomment toEntity() {
        return FeedRecomment.builder()
                .memberUuid(memberUuid)
                .commentUuid(commentUuid)
                .recommentUuid(recommentUuid)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .likeCount(likeCount)
                .dislikeCount(dislikeCount)
                .build();
    }

}
