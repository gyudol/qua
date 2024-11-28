package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedDeleteEvent;
import com.mulmeong.feed.read.api.domain.event.FeedHashtagUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedStatusUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedUpdateEvent;

public interface FeedEventHandlerService {

    void createFeedFromEvent(FeedCreateEvent event);

    void updateFeedHashtagFromEvent(FeedHashtagUpdateEvent event);

    void updateFeedStatusFromEvent(FeedStatusUpdateEvent event);

    void updateFeedFromEvent(FeedUpdateEvent event);

    void deleteFeedFromEvent(FeedDeleteEvent event);

}
