package com.mulmeong.event.contest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContestPostUpdateEvent {

    private String postUuid;

    public static ContestPostUpdateEvent toDto(String postUuid) {
        return ContestPostUpdateEvent.builder()
                .postUuid(postUuid)
                .build();
    }

}
