package com.mulmeong.contest.read.application;

import com.mulmeong.contest.read.common.exception.BaseException;
import com.mulmeong.contest.read.common.response.BaseResponseStatus;
import com.mulmeong.contest.read.common.utils.CursorPage;
import com.mulmeong.contest.read.dto.in.ContestPostRequestDto;
import com.mulmeong.contest.read.dto.out.ContestPostResponseDto;
import com.mulmeong.contest.read.entity.ContestPost;
import com.mulmeong.contest.read.infrastructure.ContestPostCustomRepository;
import com.mulmeong.contest.read.infrastructure.ContestPostRepository;
import com.mulmeong.event.contest.consume.ContestPostCreateEvent;
import com.mulmeong.event.contest.consume.ContestVoteUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContestPostServiceImpl implements ContestPostService {

    private final ContestPostRepository contestPostRepository;
    private final ContestPostCustomRepository contestPostCustomRepository;

    @Override
    public void createContestPost(ContestPostCreateEvent message) {
        log.info("this is ContestPost: {}", message);
        contestPostRepository.save(message.toEntity());
    }

    @Override
    public void updateContestVote(ContestVoteUpdateEvent message) {

        ContestPost post = contestPostRepository.findByPostUuid(message.getPostUuid()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NOT_EXIST_POST)
        );

        contestPostRepository.save(message.toEntity(post, message.getCount()));
    }

    @Override
    public CursorPage<ContestPostResponseDto> getPosts(ContestPostRequestDto requestDto) {
        return contestPostCustomRepository.getContestPosts(requestDto);
    }

}
