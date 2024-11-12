package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.ShortsRecomment;
import com.mulmeong.comment.vo.in.ShortsRecommentUpdateVo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShortsRecommentUpdateDto {
    private String content;
    private String recommentUuid;
    private String memberUuid;

    public static ShortsRecommentUpdateDto toDto(ShortsRecommentUpdateVo updateVo, String recommentUuid, String memberUuid) {
        return ShortsRecommentUpdateDto.builder()
                .content(updateVo.getContent())
                .recommentUuid(recommentUuid)
                .memberUuid(memberUuid)
                .build();
    }

    public ShortsRecomment toEntity(ShortsRecomment shortsRecomment) {
        return ShortsRecomment.builder()
                .id(shortsRecomment.getId())
                .commentUuid(shortsRecomment.getCommentUuid())
                .memberUuid(shortsRecomment.getMemberUuid())
                .recommentUuid(recommentUuid)
                .content(content)
                .build();
    }
}
