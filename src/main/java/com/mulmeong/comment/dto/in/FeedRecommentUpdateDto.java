package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.FeedRecomment;
import com.mulmeong.comment.vo.in.FeedRecommentUpdateVo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeedRecommentUpdateDto {
    private String content;
    private String recommentUuid;

    public static FeedRecommentUpdateDto toDto(FeedRecommentUpdateVo updateVo, String recommentUuid) {
        return FeedRecommentUpdateDto.builder()
                .content(updateVo.getContent())
                .recommentUuid(recommentUuid)
                .build();
    }

    public FeedRecomment toEntity(FeedRecomment feedRecomment) {
        return FeedRecomment.builder()
                .id(feedRecomment.getId())
                .commentUuid(feedRecomment.getCommentUuid())
                .memberUuid(feedRecomment.getMemberUuid())
                .recommentUuid(recommentUuid)
                .content(content)
                .build();
    }
}
