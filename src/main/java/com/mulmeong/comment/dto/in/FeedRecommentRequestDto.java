package com.mulmeong.comment.dto.in;

import com.mulmeong.comment.entity.FeedRecomment;
import com.mulmeong.comment.vo.in.FeedRecommentRequestVo;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class FeedRecommentRequestDto {

    private String commentUuid;
    private String memberUuid;
    private String content;

    public static FeedRecommentRequestDto toDto(FeedRecommentRequestVo requestVo, String commentUuid) {
        return FeedRecommentRequestDto.builder()
                .commentUuid(commentUuid)
                .memberUuid(requestVo.getMemberUuid())
                .content(requestVo.getContent())
                .build();
    }

    public FeedRecomment toEntity() {
        return FeedRecomment.builder()
                .commentUuid(commentUuid)
                .memberUuid(memberUuid)
                .recommentUuid(UUID.randomUUID().toString())
                .content(content)
                .build();
    }
}
