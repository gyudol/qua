package com.mulmeong.batchserver.feed.application;

import com.mulmeong.event.utility.consume.DislikeRenewCreateEvent;
import com.mulmeong.event.utility.consume.FeedCommentCreateEvent;
import com.mulmeong.event.utility.consume.FeedCommentDeleteEvent;
import com.mulmeong.event.utility.consume.LikeRenewCreateEvent;

public interface FeedService {
    void likeCountRenew(LikeRenewCreateEvent message);

    void dislikeCountRenew(DislikeRenewCreateEvent message);

    void feedCommentCountRenew(FeedCommentCreateEvent message);

    void feedCommentCountRenew(FeedCommentDeleteEvent message);
}
