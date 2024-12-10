package com.mulmeong.feed.read.api.application;

import static com.mulmeong.feed.read.common.response.BaseResponseStatus.FEED_NOT_FOUND;

import com.mulmeong.feed.read.api.dto.in.FeedAuthorRequestDto;
import com.mulmeong.feed.read.api.dto.in.FeedFilterRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.api.infrastructure.FeedCustomRepository;
import com.mulmeong.feed.read.api.infrastructure.FeedQueryRepository;
import com.mulmeong.feed.read.common.exception.BaseException;
import com.mulmeong.feed.read.common.utils.CursorPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FeedQueryServiceImpl implements FeedQueryService {

    private final FeedQueryRepository feedQueryRepository;
    private final FeedCustomRepository feedCustomRepository;

    @Override
    public FeedResponseDto getSingleFeed(String feedUuid) {

        return FeedResponseDto.fromDocument(feedQueryRepository.findByFeedUuid(feedUuid)
            .orElseThrow(() -> new BaseException(FEED_NOT_FOUND)));
    }

    @Override
    public CursorPage<FeedResponseDto> getFeedsByCategoryOrTag(FeedFilterRequestDto requestDto) {

        return feedCustomRepository.getFeedsByCategoryOrTag(requestDto);
    }

    @Override
    public CursorPage<FeedResponseDto> getFeedsByAuthor(FeedAuthorRequestDto requestDto) {

        return feedCustomRepository.getFeedsByAuthor(requestDto);
    }

}
