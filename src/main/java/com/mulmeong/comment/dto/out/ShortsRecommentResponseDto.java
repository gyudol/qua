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
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static ShortsRecommentResponseDto toDto(ShortsRecomment shortsRecomment) {
        return ShortsRecommentResponseDto.builder()
                .commentUuid(shortsRecomment.getCommentUuid())
                .memberUuid(shortsRecomment.getMemberUuid())
                .recommentUuid(shortsRecomment.getRecommentUuid())
                .content(shortsRecomment.getContent())
                .createAt(shortsRecomment.getCreatedAt())
                .updateAt(shortsRecomment.getUpdatedAt())
                .build();
    }

    public ShortsRecommentResponseVo toVo() {
        return ShortsRecommentResponseVo.builder()
                .commentUuid(commentUuid)
                .memberUuid(memberUuid)
                .recommentUuid(recommentUuid)
                .content(content)
                .createAt(createAt)
                .updateAt(updateAt)
                .build();
    }
}
