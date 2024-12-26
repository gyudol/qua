package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.ShortsComment;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShortsCommentDeleteDto {

    public static ShortsComment toEntity(ShortsComment shortsComment) {
        return ShortsComment.builder()
                .id(shortsComment.getId())
                .shortsUuid(shortsComment.getShortsUuid())
                .memberUuid(shortsComment.getMemberUuid())
                .commentUuid(shortsComment.getCommentUuid())
                .content(shortsComment.getContent())
                .isDeleted(true)
                .build();
    }
}
