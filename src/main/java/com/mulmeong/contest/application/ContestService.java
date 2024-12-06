package com.mulmeong.contest.application;

import com.mulmeong.contest.common.utils.CursorPage;
import com.mulmeong.contest.dto.in.ContestQueryRequestDto;
import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRequestDto;
import com.mulmeong.contest.dto.out.ContestResponseDto;
import com.mulmeong.event.contest.consume.ContestStatusEvent;
import com.mulmeong.event.contest.consume.ContestVoteResultEvent;

public interface ContestService {

    void openContest(ContestRequestDto dto);

    void applyContest(PostRequestDto dto);

    void vote(PostVoteRequestDto dto, String memberUuid);

    CursorPage<ContestResponseDto> getContests(ContestQueryRequestDto requestDto);

    void createContestResult(ContestVoteResultEvent message);

    void altContestStatus(ContestStatusEvent message);
}
