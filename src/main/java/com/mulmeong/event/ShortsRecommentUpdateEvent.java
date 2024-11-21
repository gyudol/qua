package com.mulmeong.event;

import com.mulmeong.comment.read.entity.ShortsRecomment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class ShortsRecommentUpdateEvent {

    private String recommentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public ShortsRecommentUpdateEvent(ShortsRecommentUpdateEvent message) {
        this.recommentUuid = message.getRecommentUuid();
        this.content = message.getContent();
        this.updatedAt = message.getUpdatedAt();
    }

    public ShortsRecomment toEntity(ShortsRecomment shortsRecomment) {
        return ShortsRecomment.builder()
                .id(shortsRecomment.getId())
                .memberUuid(shortsRecomment.getMemberUuid())
                .commentUuid(shortsRecomment.getCommentUuid())
                .recommentUuid(recommentUuid)
                .content(content)
                .createdAt(shortsRecomment.getCreatedAt())
                .updatedAt(updatedAt)
                .likeCount(shortsRecomment.getLikeCount())
                .dislikeCount(shortsRecomment.getDislikeCount())
                .build();
    }
}
