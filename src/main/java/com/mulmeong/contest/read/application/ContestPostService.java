package com.mulmeong.contest.read.application;

import com.mulmeong.event.contest.consume.ContestPostCreateEvent;
import com.mulmeong.event.contest.consume.ContestVoteUpdateEvent;

public interface ContestPostService {
    void createContestPost(ContestPostCreateEvent message);

    void updateContestVote(ContestVoteUpdateEvent message);
}
