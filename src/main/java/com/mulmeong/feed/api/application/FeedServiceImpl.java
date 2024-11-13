package com.mulmeong.feed.api.application;

import static com.mulmeong.feed.common.response.BaseResponseStatus.FEED_NOT_FOUND;

import com.mulmeong.feed.api.dto.in.CreateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedRequestDto;
import com.mulmeong.feed.api.infrastructure.FeedHashtagRepository;
import com.mulmeong.feed.api.infrastructure.FeedMediaRepository;
import com.mulmeong.feed.api.infrastructure.FeedRepository;
import com.mulmeong.feed.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final FeedMediaRepository feedMediaRepository;
    private final FeedHashtagRepository feedHashtagRepository;

    @Transactional
    @Override
    public void createFeed(CreateFeedRequestDto requestDto) {

        feedRepository.save(requestDto.toFeedEntity());
        feedMediaRepository.saveAll(requestDto.toFeedMediaEntities());
        feedHashtagRepository.saveAll(requestDto.toFeedHashtagEntities());
    }

    @Transactional
    @Override
    public void updateFeed(UpdateFeedRequestDto requestDto) {

        feedRepository.save(requestDto.toFeedEntity(
            feedRepository.findByFeedUuidAndMemberUuid(requestDto.getFeedUuid(),
                    requestDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND)).getId()));

        // FeedHashtag 전체 삭제 후 재삽입
        feedHashtagRepository.deleteAllByFeedUuid(requestDto.getFeedUuid());
        feedHashtagRepository.saveAll(requestDto.toFeedHashtagEntities());
    }

    @Transactional
    @Override
    public void deleteFeed(String feedUuid, String memberUuid) {

        feedRepository.deleteByFeedUuidAndMemberUuid(feedUuid, memberUuid);
        feedHashtagRepository.deleteAllByFeedUuid(feedUuid);
        feedMediaRepository.deleteAllByFeedUuid(feedUuid);
    }

}
