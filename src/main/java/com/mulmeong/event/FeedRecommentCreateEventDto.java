package com.mulmeong.event;

import com.mulmeong.comment.entity.FeedRecomment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedRecommentCreateEventDto {

    private String memberUuid;
    private String commentUuid;
    private String recommentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public static FeedRecommentCreateEventDto toDto(FeedRecomment feedRecomment) {
        return FeedRecommentCreateEventDto.builder()
                .commentUuid(feedRecomment.getCommentUuid())
                .recommentUuid(feedRecomment.getRecommentUuid())
                .memberUuid(feedRecomment.getMemberUuid())
                .content(feedRecomment.getContent())
                .createdAt(feedRecomment.getCreatedAt())
                .updatedAt(feedRecomment.getUpdatedAt())
                .likeCount(0)
                .dislikeCount(0)
                .build();
    }




}
