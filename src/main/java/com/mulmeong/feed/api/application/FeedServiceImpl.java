package com.mulmeong.feed.api.application;

import com.mulmeong.feed.api.dto.in.CreateFeedRequestDto;
import com.mulmeong.feed.api.infrastructure.FeedHashtagRepository;
import com.mulmeong.feed.api.infrastructure.FeedMediaRepoeitory;
import com.mulmeong.feed.api.infrastructure.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final FeedMediaRepoeitory feedMediaRepoeitory;
    private final FeedHashtagRepository feedHashtagRepository;

    @Transactional
    @Override
    public void createFeed(CreateFeedRequestDto requestDto) {
        feedRepository.save(requestDto.toFeedEntity());
        feedMediaRepoeitory.saveAll(requestDto.toFeedMediaEntityList());
        feedHashtagRepository.saveAll(requestDto.toFeedHashtagEntityList());
    }

}
