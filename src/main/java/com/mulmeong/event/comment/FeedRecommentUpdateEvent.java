package com.mulmeong.event.comment;

import com.mulmeong.comment.read.entity.FeedRecomment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class FeedRecommentUpdateEvent {

    private String recommentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public FeedRecomment toEntity(FeedRecomment feedRecomment) {
        return FeedRecomment.builder()
                .id(feedRecomment.getId())
                .memberUuid(feedRecomment.getMemberUuid())
                .commentUuid(feedRecomment.getCommentUuid())
                .recommentUuid(recommentUuid)
                .content(content)
                .createdAt(feedRecomment.getCreatedAt())
                .updatedAt(updatedAt)
                .likeCount(feedRecomment.getLikeCount())
                .dislikeCount(feedRecomment.getDislikeCount())
                .build();
    }
}
