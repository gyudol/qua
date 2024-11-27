package com.mulmeong.contest.read.application;

import com.mulmeong.contest.read.infrastructure.ContestPostRepository;
import com.mulmeong.event.contest.ContestPostCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContestPostServiceImpl implements ContestPostService{

    private final ContestPostRepository contestPostRepository;

    @Override
    public void createContestPost(ContestPostCreateEvent message) {
        log.info("this is ContestPost: {}", message);
        contestPostRepository.save(message.toEntity());
    }
}
