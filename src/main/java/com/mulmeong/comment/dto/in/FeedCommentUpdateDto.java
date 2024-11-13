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
    private String memberUuid;

    public static FeedCommentUpdateDto toDto(
            FeedCommentUpdateVo updateVo, String commentUuid, String memberUuid) {
        return FeedCommentUpdateDto.builder()
                .content(updateVo.getContent())
                .commentUuid(commentUuid)
                .memberUuid(memberUuid)
                .build();
    }

    public FeedComment toEntity(FeedComment feedComment) {
        return FeedComment.builder()
                .id(feedComment.getId())
                .memberUuid(memberUuid)
                .feedUuid(feedComment.getFeedUuid())
                .commentUuid(commentUuid)
                .content(content)
                .status(feedComment.isStatus())
                .build();
    }

}
