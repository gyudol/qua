package com.mulmeong.batchserver.shorts.application;

import com.mulmeong.event.utility.consume.DislikeRenewCreateEvent;
import com.mulmeong.event.utility.consume.LikeRenewCreateEvent;

public interface ShortsService {
    void likeCountRenew(LikeRenewCreateEvent message);

    void dislikeCountRenew(DislikeRenewCreateEvent message);
}
