package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedDeleteEvent;

public interface FeedEventHandlerService {

    void createFeedFromEvent(FeedCreateEvent event);

    void deleteFeedFromEvent(FeedDeleteEvent event);

}
