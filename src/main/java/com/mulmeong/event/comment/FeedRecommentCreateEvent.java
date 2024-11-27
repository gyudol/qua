package com.mulmeong.event.comment;

import com.mulmeong.comment.entity.FeedRecomment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedRecommentCreateEvent {

    private String memberUuid;
    private String commentUuid;
    private String recommentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FeedRecommentCreateEvent toDto(FeedRecomment feedRecomment) {
        return FeedRecommentCreateEvent.builder()
                .commentUuid(feedRecomment.getCommentUuid())
                .recommentUuid(feedRecomment.getRecommentUuid())
                .memberUuid(feedRecomment.getMemberUuid())
                .content(feedRecomment.getContent())
                .createdAt(feedRecomment.getCreatedAt())
                .updatedAt(feedRecomment.getUpdatedAt())
                .build();
    }




}
