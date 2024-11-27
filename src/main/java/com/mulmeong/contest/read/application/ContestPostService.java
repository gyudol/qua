package com.mulmeong.contest.read.application;

import com.mulmeong.event.contest.ContestPostCreateEvent;

public interface ContestPostService {
    void createContestPost(ContestPostCreateEvent message);
}
