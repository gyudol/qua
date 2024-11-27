package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.ShortsRecomment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ShortsRecommentUpdateEvent {

    private String recommentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public static ShortsRecommentUpdateEvent toDto(ShortsRecomment shortsRecomment, LocalDateTime updatedAt) {
        return ShortsRecommentUpdateEvent.builder()
                .recommentUuid(shortsRecomment.getRecommentUuid())
                .content(shortsRecomment.getContent())
                .updatedAt(updatedAt)
                .build();
    }
}
