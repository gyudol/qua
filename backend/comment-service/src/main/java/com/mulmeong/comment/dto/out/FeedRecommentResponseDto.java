package com.mulmeong.comment.dto.out;

import com.mulmeong.comment.entity.FeedRecomment;
import com.mulmeong.comment.vo.out.FeedRecommentResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FeedRecommentResponseDto {

    private String commentUuid;
    private String memberUuid;
    private String recommentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FeedRecommentResponseDto toDto(FeedRecomment feedRecomment) {
        return FeedRecommentResponseDto.builder()
                .commentUuid(feedRecomment.getCommentUuid())
                .memberUuid(feedRecomment.getMemberUuid())
                .recommentUuid(feedRecomment.getRecommentUuid())
                .content(feedRecomment.getContent())
                .createdAt(feedRecomment.getCreatedAt())
                .updatedAt(feedRecomment.getUpdatedAt())
                .build();
    }

    public FeedRecommentResponseVo toVo() {
        return FeedRecommentResponseVo.builder()
                .commentUuid(commentUuid)
                .memberUuid(memberUuid)
                .recommentUuid(recommentUuid)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
