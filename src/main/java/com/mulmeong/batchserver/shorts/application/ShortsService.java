package com.mulmeong.batchserver.shorts.application;

import com.mulmeong.event.utility.consume.DislikesCreateEvent;
import com.mulmeong.event.utility.consume.LikesCreateEvent;

public interface ShortsService {
    void likeCountRenew(LikesCreateEvent message);

    void dislikeCountRenew(DislikesCreateEvent message);
}
