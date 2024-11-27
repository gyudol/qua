package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.entity.FeedComment;
import com.mulmeong.comment.vo.in.FeedCommentRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class FeedCommentRequestDto {

    private String memberUuid;
    private String feedUuid;
    private String content;
    private String contentUuid;
    private boolean isDeleted;

    public static FeedCommentRequestDto toDto(FeedCommentRequestVo requestVo, String feedUuid) {
        return FeedCommentRequestDto.builder()
                .memberUuid(requestVo.getMemberUuid())
                .feedUuid(feedUuid)
                .content(requestVo.getContent())
                .build();
    }

    public FeedComment toEntity() {
        return FeedComment.builder()
                .memberUuid(memberUuid)
                .feedUuid(feedUuid)
                .commentUuid(UUID.randomUUID().toString())
                .content(content)
                .isDeleted(false)
                .build();
    }
}
