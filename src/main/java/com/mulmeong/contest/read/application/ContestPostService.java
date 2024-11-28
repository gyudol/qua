package com.mulmeong.contest.read.application;

import com.mulmeong.event.contest.ContestPostCreateEvent;
import com.mulmeong.event.contest.ContestPostUpdateEvent;

public interface ContestPostService {
    void createContestPost(ContestPostCreateEvent message);

    void updateContestPost(ContestPostUpdateEvent message);
}
