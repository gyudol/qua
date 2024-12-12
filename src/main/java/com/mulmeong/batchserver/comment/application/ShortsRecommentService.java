package com.mulmeong.batchserver.comment.application;

import com.mulmeong.event.utility.consume.DislikesCreateEvent;
import com.mulmeong.event.utility.consume.LikesCreateEvent;

public interface ShortsRecommentService {

    void likeCountRenew(LikesCreateEvent message);

    void dislikeCountRenew(DislikesCreateEvent message);

}
