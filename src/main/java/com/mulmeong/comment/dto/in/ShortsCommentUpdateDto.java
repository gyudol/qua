package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.ShortsComment;
import com.mulmeong.comment.vo.in.ShortsCommentUpdateVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ShortsCommentUpdateDto {
    private String commentUuid;
    private String content;
    private String memberUuid;

    public static ShortsCommentUpdateDto toDto(
            ShortsCommentUpdateVo updateVo, String commentUuid, String memberUuid) {
        return ShortsCommentUpdateDto.builder()
                .commentUuid(commentUuid)
                .content(updateVo.getContent())
                .memberUuid(memberUuid)
                .build();
    }

    public ShortsComment toEntity(ShortsComment shortsComment) {
        return ShortsComment.builder()
                .id(shortsComment.getId())
                .shortsUuid(shortsComment.getShortsUuid())
                .memberUuid(memberUuid)
                .commentUuid(commentUuid)
                .content(content)
                .status(shortsComment.isStatus())
                .build();
    }

}
