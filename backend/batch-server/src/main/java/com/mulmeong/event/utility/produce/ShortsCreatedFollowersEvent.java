package com.mulmeong.event.utility.produce;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ShortsCreatedFollowersEvent {
    private String shortsUuid;
    private String memberUuid;
    private List<String> followerUuids;
    private String title;

    public static ShortsCreatedFollowersEvent toDto(
            String shortsUuid,
            String memberUuid,
            List<String> followerUuids,
            String title
    ) {
        return ShortsCreatedFollowersEvent.builder()
                .shortsUuid(shortsUuid)
                .memberUuid(memberUuid)
                .followerUuids(followerUuids)
                .title(title)
                .build();
    }
}
