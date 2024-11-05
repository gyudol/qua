package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.ShortsComment;
import com.mulmeong.comment.vo.in.ShortsCommentUpdateVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ShortsCommentUpdateDto {
    private String commentUuid;
    private String content;

    public static ShortsCommentUpdateDto toDto(ShortsCommentUpdateVo updateVo, String commentUuid) {
        return ShortsCommentUpdateDto.builder()
                .commentUuid(commentUuid)
                .content(updateVo.getContent())
                .build();
    }

    public ShortsComment toEntity(ShortsComment shortsComment) {
        return ShortsComment.builder()
                .shortsUuid(shortsComment.getShortsUuid())
                .memberUuid(shortsComment.getMemberUuid())
                .commentUuid(commentUuid)
                .content(content)
                .status(shortsComment.getStatus())
                .build();
    }

}
