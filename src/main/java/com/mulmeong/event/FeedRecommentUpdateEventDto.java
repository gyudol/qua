package com.mulmeong.event;

import com.mulmeong.comment.entity.FeedRecomment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedRecommentUpdateEventDto {

    private String recommentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public static FeedRecommentUpdateEventDto toDto(FeedRecomment feedRecomment) {
        return FeedRecommentUpdateEventDto.builder()
                .recommentUuid(feedRecomment.getRecommentUuid())
                .content(feedRecomment.getContent())
                .updatedAt(feedRecomment.getUpdatedAt())
                .build();
    }
}
