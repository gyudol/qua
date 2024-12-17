package com.mulmeong.batchserver.shorts.application;

import com.mulmeong.event.utility.consume.DislikeRenewCreateEvent;
import com.mulmeong.event.utility.consume.LikeRenewCreateEvent;
import com.mulmeong.event.utility.consume.ShortsCommentCreateEvent;
import com.mulmeong.event.utility.consume.ShortsCommentDeleteEvent;

public interface ShortsService {
    void likeCountRenew(LikeRenewCreateEvent message);

    void dislikeCountRenew(DislikeRenewCreateEvent message);

    void shortsCommentCountRenew(ShortsCommentCreateEvent message);

    void shortsCommentCountRenew(ShortsCommentDeleteEvent message);
}
