package com.mulmeong.contest.application;

import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRequestDto;
import com.mulmeong.contest.entity.ContestPost;
import com.mulmeong.contest.entity.ContestWinner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContestService {

    void openContest(ContestRequestDto dto);

    void applyContest(PostRequestDto dto);

    void vote(PostVoteRequestDto dto, String memberUuid);

    @Transactional
    void calculateWinners(Long contestId);
}
