package com.mulmeong.event.utility.produce;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FeedCreatedFollowersEvent {
    private String feedUuid;
    private String memberUuid;
    private List<String> followerUuids;
    private String title;

    public static FeedCreatedFollowersEvent toDto(
            String feedUuid,
            String memberUuid,
            List<String> followerUuids,
            String title
    ) {
        return FeedCreatedFollowersEvent.builder()
                .feedUuid(feedUuid)
                .memberUuid(memberUuid)
                .followerUuids(followerUuids)
                .title(title)
                .build();
    }
}
