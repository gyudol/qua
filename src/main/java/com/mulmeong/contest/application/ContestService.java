package com.mulmeong.contest.application;

import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRenewRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRequestDto;
import com.mulmeong.event.contest.ContestPostUpdateEvent;
import com.mulmeong.event.contest.ContestVoteRenewEvent;

public interface ContestService {

    void openContest(ContestRequestDto dto);

    void applyContest(PostRequestDto dto);

    void vote(PostVoteRequestDto dto, String memberUuid);

    void voteRenew(ContestVoteRenewEvent message);

}
