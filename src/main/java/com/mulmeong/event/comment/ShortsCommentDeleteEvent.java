package com.mulmeong.event.comment;

import com.mulmeong.comment.read.entity.ShortsComment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ShortsCommentDeleteEvent {

    private String commentUuid;
    private boolean isDeleted;

    public ShortsComment toEntity(ShortsComment shortsComment) {
        return ShortsComment.builder()
                .id(shortsComment.getId())
                .shortsUuid(shortsComment.getShortsUuid())
                .memberUuid(shortsComment.getMemberUuid())
                .commentUuid(commentUuid)
                .content(shortsComment.getContent())
                .isDeleted(isDeleted)
                .createdAt(shortsComment.getCreatedAt())
                .updatedAt(shortsComment.getUpdatedAt())
                .likeCount(shortsComment.getLikeCount())
                .dislikeCount(shortsComment.getDislikeCount())
                .recommentCount(shortsComment.getRecommentCount())
                .build();
    }
}
