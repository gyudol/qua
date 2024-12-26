package com.mulmeong.feed.api.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FeedDeleteEvent {

    private String feedUuid;
    private String memberUuid;

}
