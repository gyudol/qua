package com.mulmeong.batchserver.feed.application;

import com.mulmeong.event.utility.consume.DislikeRenewCreateEvent;
import com.mulmeong.event.utility.consume.LikeRenewCreateEvent;

public interface FeedService {
    void likeCountRenew(LikeRenewCreateEvent message);

    void dislikeCountRenew(DislikeRenewCreateEvent message);
}
