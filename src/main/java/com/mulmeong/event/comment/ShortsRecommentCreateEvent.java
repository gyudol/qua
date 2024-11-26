package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.ShortsRecomment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ShortsRecommentCreateEvent {

    private String memberUuid;
    private String commentUuid;
    private String recommentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public static ShortsRecommentCreateEvent toDto(ShortsRecomment shortsRecomment) {
        return ShortsRecommentCreateEvent.builder()
                .commentUuid(shortsRecomment.getCommentUuid())
                .recommentUuid(shortsRecomment.getRecommentUuid())
                .memberUuid(shortsRecomment.getMemberUuid())
                .content(shortsRecomment.getContent())
                .createdAt(shortsRecomment.getCreatedAt())
                .updatedAt(shortsRecomment.getUpdatedAt())
                .likeCount(0)
                .dislikeCount(0)
                .build();
    }




}
