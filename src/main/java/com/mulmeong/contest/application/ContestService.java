package com.mulmeong.contest.application;

import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;

public interface ContestService {
    void openContest(ContestRequestDto dto);

    void applyContest(PostRequestDto dto);
}
