package com.mulmeong.batchserver.feed.application;

import com.mulmeong.event.utility.consume.DislikesCreateEvent;
import com.mulmeong.event.utility.consume.LikesCreateEvent;

public interface FeedService {
    void likeCountRenew(LikesCreateEvent message);

    void dislikeCountRenew(DislikesCreateEvent message);
}
