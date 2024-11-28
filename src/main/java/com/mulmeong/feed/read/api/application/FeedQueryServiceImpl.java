package com.mulmeong.feed.read.api.application;

import static com.mulmeong.feed.read.common.response.BaseResponseStatus.FEED_NOT_FOUND;

import com.mulmeong.feed.read.api.dto.FeedResponseDto;
import com.mulmeong.feed.read.api.infrastructure.FeedQueryRepository;
import com.mulmeong.feed.read.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedQueryServiceImpl implements FeedQueryService {

    private final FeedQueryRepository feedQueryRepository;

    @Transactional
    @Override
    public FeedResponseDto getSingleFeed(String feedUuid) {

        return FeedResponseDto.fromDocument(feedQueryRepository.findById(feedUuid)
            .orElseThrow(() -> new BaseException(FEED_NOT_FOUND)));
    }

}
