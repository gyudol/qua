package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.ShortsRecomment;
import com.mulmeong.comment.vo.in.ShortsRecommentRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ShortsRecommentRequestDto {

    private String commentUuid;
    private String memberUuid;
    private String recommentUuid;
    private String content;

    public static ShortsRecommentRequestDto toDto(ShortsRecommentRequestVo requestVo, String commentUuid) {
        return ShortsRecommentRequestDto.builder()
                .commentUuid(commentUuid)
                .memberUuid(requestVo.getMemberUuid())
                .content(requestVo.getContent())
                .build();
    }

    public ShortsRecomment toEntity() {
        return ShortsRecomment.builder()
                .commentUuid(commentUuid)
                .memberUuid(memberUuid)
                .recommentUuid(UUID.randomUUID().toString())
                .content(content)
                .build();
    }
}
