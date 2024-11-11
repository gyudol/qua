package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.ShortsComment;
import com.mulmeong.comment.vo.in.ShortsCommentRequestVo;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ShortsCommentRequestDto {

    private String shortsUuid;
    private String memberUuid;
    private String commentUuid;
    private String content;
    private Boolean status;

    public static ShortsCommentRequestDto toDto(ShortsCommentRequestVo requestVo, String shortsUuid) {
        return ShortsCommentRequestDto.builder()
                .shortsUuid(shortsUuid)
                .memberUuid(requestVo.getMemberUuid())
                .content(requestVo.getContent())
                .build();
    }

    public ShortsComment toEntity() {
        return ShortsComment.builder()
                .shortsUuid(shortsUuid)
                .memberUuid(memberUuid)
                .commentUuid(UUID.randomUUID().toString())
                .content(content)
                .status(true)
                .build();
    }
}
