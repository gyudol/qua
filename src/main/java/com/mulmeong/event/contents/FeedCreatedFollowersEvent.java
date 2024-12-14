package com.mulmeong.event.contents;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class FeedCreatedFollowersEvent {
    private String feedUuid;
    private String memberUuid;
    private List<String> followerUuids;
    private String title;
}
