package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;

public interface FeedEventHandlerService {

    void createFeedFromEvent(FeedCreateEvent event);

}
