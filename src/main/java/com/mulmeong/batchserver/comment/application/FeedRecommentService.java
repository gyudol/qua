package com.mulmeong.batchserver.comment.application;

import com.mulmeong.event.utility.consume.DislikeRenewCreateEvent;
import com.mulmeong.event.utility.consume.LikeRenewCreateEvent;

public interface FeedRecommentService {

    void likeCountRenew(LikeRenewCreateEvent message);

    void dislikeCountRenew(DislikeRenewCreateEvent message);

}
