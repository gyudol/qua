package com.mulmeong.event.comment;

import com.mulmeong.comment.read.entity.ShortsComment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class ShortsCommentUpdateEvent {

    private String commentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public ShortsComment toEntity(ShortsComment shortsComment) {
        return ShortsComment.builder()
                .id(shortsComment.getId())
                .shortsUuid(shortsComment.getShortsUuid())
                .memberUuid(shortsComment.getMemberUuid())
                .commentUuid(commentUuid)
                .content(content)
                .status(shortsComment.isStatus())
                .createdAt(shortsComment.getCreatedAt())
                .updatedAt(updatedAt)
                .likeCount(shortsComment.getLikeCount())
                .dislikeCount(shortsComment.getDislikeCount())
                .recommentCount(shortsComment.getRecommentCount())
                .build();
    }
}
