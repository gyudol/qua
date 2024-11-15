package com.mulmeong.contest.application;

import com.mulmeong.contest.common.exception.BaseException;
import com.mulmeong.contest.common.response.BaseResponseStatus;
import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.entity.Contest;
import com.mulmeong.contest.entity.Post;
import com.mulmeong.contest.infrastructure.ContestRepository;
import com.mulmeong.contest.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContestServiceImpl implements ContestService {

    private final ContestRepository contestRepository;
    private final PostRepository postRepository;

    @Override
    public void openContest(ContestRequestDto dto) {
        contestRepository.save(dto.toEntity());
    }

    @Override
    public void applyContest(PostRequestDto dto) {

        Contest contest = contestRepository.findById(dto.getContestId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXIST));

        LocalDate now = LocalDate.now();

        if (now.isBefore(contest.getStartDate()) || now.isAfter(contest.getEndDate())) {
            throw new BaseException(BaseResponseStatus.NOT_IN_TIME);
        }

        postRepository.save(dto.toEntity());
    }

}
