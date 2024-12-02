package com.mulmeong.contest.read.application;

import com.mulmeong.contest.read.common.utils.CursorPage;
import com.mulmeong.contest.read.dto.in.ContestPostRequestDto;
import com.mulmeong.contest.read.dto.out.ContestPostResponseDto;
import com.mulmeong.event.contest.consume.ContestPostCreateEvent;
import com.mulmeong.event.contest.consume.ContestVoteUpdateEvent;

public interface ContestPostService {
    void createContestPost(ContestPostCreateEvent message);

    void updateContestVote(ContestVoteUpdateEvent message);

    CursorPage<ContestPostResponseDto> getPosts(ContestPostRequestDto requestDto);
}
