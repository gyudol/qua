package com.mulmeong.comment.dto.out;

import com.mulmeong.comment.entity.ShortsRecomment;
import com.mulmeong.comment.vo.out.ShortsRecommentResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ShortsRecommentResponseDto {

    private String commentUuid;
    private String memberUuid;
    private String recommentUuid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ShortsRecommentResponseDto toDto(ShortsRecomment shortsRecomment) {
        return ShortsRecommentResponseDto.builder()
                .commentUuid(shortsRecomment.getCommentUuid())
                .memberUuid(shortsRecomment.getMemberUuid())
                .recommentUuid(shortsRecomment.getRecommentUuid())
                .content(shortsRecomment.getContent())
                .createdAt(shortsRecomment.getCreatedAt())
                .updatedAt(shortsRecomment.getUpdatedAt())
                .build();
    }

    public ShortsRecommentResponseVo toVo() {
        return ShortsRecommentResponseVo.builder()
                .commentUuid(commentUuid)
                .memberUuid(memberUuid)
                .recommentUuid(recommentUuid)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
