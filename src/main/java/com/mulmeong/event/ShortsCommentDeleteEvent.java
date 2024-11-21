package com.mulmeong.event;

import com.mulmeong.comment.read.entity.ShortsComment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ShortsCommentDeleteEvent {

    private String commentUuid;
    private boolean status;

    public ShortsCommentDeleteEvent(ShortsCommentDeleteEvent message) {
        this.commentUuid = message.getCommentUuid();
        this.status = message.isStatus();
    }

    public ShortsComment toEntity(ShortsComment shortsComment) {
        return ShortsComment.builder()
                .id(shortsComment.getId())
                .shortsUuid(shortsComment.getShortsUuid())
                .memberUuid(shortsComment.getMemberUuid())
                .commentUuid(commentUuid)
                .content(shortsComment.getContent())
                .status(status)
                .createdAt(shortsComment.getCreatedAt())
                .updatedAt(shortsComment.getUpdatedAt())
                .likeCount(shortsComment.getLikeCount())
                .dislikeCount(shortsComment.getDislikeCount())
                .recommentCount(shortsComment.getRecommentCount())
                .build();
    }
}
