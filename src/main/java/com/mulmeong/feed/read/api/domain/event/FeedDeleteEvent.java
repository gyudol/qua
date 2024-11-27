package com.mulmeong.feed.read.api.domain.event;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedDeleteEvent {

    private String feedUuid;

}
