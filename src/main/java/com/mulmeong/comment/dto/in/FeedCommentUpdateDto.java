package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.FeedComment;
import com.mulmeong.comment.vo.in.FeedCommentUpdateVo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeedCommentUpdateDto {

    private String content;
    private String commentUuid;

    public static FeedCommentUpdateDto toDto(FeedCommentUpdateVo updateVo, String commentUuid) {
        return FeedCommentUpdateDto.builder()
                .content(updateVo.getContent())
                .commentUuid(commentUuid)
                .build();
    }

    public FeedComment toEntity(FeedComment feedComment) {
        return FeedComment.builder()
                .id(feedComment.getId())
                .memberUuid(feedComment.getMemberUuid())
                .feedUuid(feedComment.getFeedUuid())
                .commentUuid(commentUuid)
                .content(content)
                .status(feedComment.isStatus())
                .build();
    }

}
