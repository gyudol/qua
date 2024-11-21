package com.mulmeong.feed.api.application;

import static com.mulmeong.feed.common.response.BaseResponseStatus.FEED_FORBIDDEN;
import static com.mulmeong.feed.common.response.BaseResponseStatus.FEED_NOT_FOUND;

import com.mulmeong.feed.api.dto.in.CreateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedStatusRequestDto;
import com.mulmeong.feed.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.api.infrastructure.FeedHashtagRepository;
import com.mulmeong.feed.api.infrastructure.FeedKafkaProducer;
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
    private final FeedKafkaProducer feedKafkaProducer;

    @Transactional
    @Override
    public void createFeed(CreateFeedRequestDto requestDto) {

        feedRepository.save(requestDto.toFeedEntity());
        feedMediaRepository.saveAll(requestDto.toFeedMediaEntities());
        feedHashtagRepository.saveAll(requestDto.toFeedHashtagEntities());
        feedKafkaProducer.send("feed-created", requestDto.toEventEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public FeedResponseDto getFeedDetail(String feedUuid) {    // TODO: Feed-Read Service로 옮길 예정

        return FeedResponseDto.fromEntity(feedRepository.findByFeedUuid(feedUuid)
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND)),
            feedHashtagRepository.findByFeedUuid(feedUuid),
            feedMediaRepository.findByFeedUuid(feedUuid));
    }

    @Transactional
    @Override
    public void updateFeed(UpdateFeedRequestDto requestDto) {

        // memberUuid validation & Update Feed
        feedRepository.save(
            requestDto.toFeedEntity(feedRepository.findByFeedUuid(requestDto.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));

        // TODO: Hashtag NoSQL로 재작업
        // FeedHashtag 전체 삭제 후 재삽입
        feedHashtagRepository.deleteAllByFeedUuid(requestDto.getFeedUuid());
        feedHashtagRepository.saveAll(requestDto.toFeedHashtagEntities());
    }

    @Transactional
    @Override
    public void updateFeedStatus(UpdateFeedStatusRequestDto requestDto) {

        // only update visibility
        feedRepository.save(requestDto.toFeedEntity(
            feedRepository.findByFeedUuid(requestDto.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
    }

    @Transactional
    @Override
    public void deleteFeed(String feedUuid) {

        feedRepository.delete(feedRepository.findByFeedUuid(feedUuid)
            .orElseThrow(() -> new BaseException(FEED_FORBIDDEN)));
        feedHashtagRepository.deleteAllByFeedUuid(feedUuid);
        feedMediaRepository.deleteAllByFeedUuid(feedUuid);
    }

}
