package com.mulmeong.batchserver.member.application;

import com.mulmeong.event.utility.consume.FeedCreateEvent;
import com.mulmeong.event.utility.consume.ShortsCreateEvent;

public interface MemberService {
    void updateFeedCount(FeedCreateEvent message);

    void updateShortsCount(ShortsCreateEvent message);
}
