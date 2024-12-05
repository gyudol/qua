package com.mulmeong.batchserver.utility.application;

import com.mulmeong.event.utility.consume.FeedCreateEvent;

public interface FollowService {
    void createFeedFollowerAlert(FeedCreateEvent message);
}
