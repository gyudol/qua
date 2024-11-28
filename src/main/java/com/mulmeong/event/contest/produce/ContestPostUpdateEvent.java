package com.mulmeong.event.contest.produce;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContestPostUpdateEvent {

    private String postUuid;
    private Integer count;

    public static ContestPostUpdateEvent toDto(String postUuid, Integer count) {
        return ContestPostUpdateEvent.builder()
                .postUuid(postUuid)
                .count(count)
                .build();
    }

}
