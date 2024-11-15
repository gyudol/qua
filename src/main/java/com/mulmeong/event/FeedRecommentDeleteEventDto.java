package com.mulmeong.event;

import com.mulmeong.comment.entity.FeedRecomment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedRecommentDeleteEventDto {

    private String recommentUuid;

    public static FeedRecommentDeleteEventDto toDto(String recommentUuid) {
        return FeedRecommentDeleteEventDto.builder()
                .recommentUuid(recommentUuid)
                .build();
    }
}
