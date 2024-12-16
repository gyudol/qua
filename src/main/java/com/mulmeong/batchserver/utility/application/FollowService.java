package com.mulmeong.batchserver.utility.application;

import com.mulmeong.event.utility.consume.FeedCreateEvent;
import com.mulmeong.event.utility.consume.FollowCreateEvent;
import com.mulmeong.event.utility.consume.ShortsCreateEvent;

public interface FollowService {
    void createFeedFollowerAlert(FeedCreateEvent message);

    void createShortsFollowerAlert(ShortsCreateEvent message);

    void createFollowerRenew(FollowCreateEvent message);
}
