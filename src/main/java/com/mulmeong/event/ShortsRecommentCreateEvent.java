package com.mulmeong.event;


import com.mulmeong.comment.read.entity.FeedRecomment;
import com.mulmeong.comment.read.entity.ShortsRecomment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class ShortsRecommentCreateEvent {

    private String memberUuid;
    private String commentUuid;
    private String recommentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public ShortsRecommentCreateEvent(ShortsRecommentCreateEvent message) {
        this.memberUuid = message.getMemberUuid();
        this.commentUuid = message.getCommentUuid();
        this.recommentUuid = message.getRecommentUuid();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.updatedAt = message.getUpdatedAt();
        this.likeCount = message.getLikeCount();
        this.dislikeCount = message.getDislikeCount();
    }

    public ShortsRecomment toEntity() {
        return ShortsRecomment.builder()
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
