package com.mulmeong.event;

import com.mulmeong.comment.entity.FeedComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedCommentUpdateEventDto {

    private String commentUuid;
    private String content;
    private LocalDateTime updatedAt;

    public static FeedCommentUpdateEventDto toDto(FeedComment feedcomment) {
        System.out.println(feedcomment.getUpdatedAt());
        return FeedCommentUpdateEventDto.builder()
                .commentUuid(feedcomment.getCommentUuid())
                .content(feedcomment.getContent())
                .updatedAt(feedcomment.getUpdatedAt())
                .build();
    }
}
