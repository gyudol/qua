package com.mulmeong.feed.api.application;

import static com.mulmeong.feed.common.response.BaseResponseStatus.FEED_FORBIDDEN;
import static com.mulmeong.feed.common.response.BaseResponseStatus.FEED_NOT_FOUND;

import com.mulmeong.feed.api.domain.entity.Feed;
import com.mulmeong.feed.api.domain.entity.FeedHashtag;
import com.mulmeong.feed.api.domain.event.DeleteFeedEvent;
import com.mulmeong.feed.api.dto.in.CreateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedHashtagRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedStatusRequestDto;
import com.mulmeong.feed.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.api.infrastructure.FeedHashtagRepository;
import com.mulmeong.feed.api.infrastructure.FeedKafkaProducer;
import com.mulmeong.feed.api.infrastructure.FeedMediaRepository;
import com.mulmeong.feed.api.infrastructure.FeedRepository;
import com.mulmeong.feed.common.exception.BaseException;
import java.time.LocalDateTime;
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
        feedHashtagRepository.save(requestDto.toFeedHashtagEntity());
        feedKafkaProducer.send("feed-created", requestDto.toEventEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public FeedResponseDto getFeedDetail(String feedUuid) {    // TODO: Feed-Read Service로 옮길 예정

        return FeedResponseDto.fromEntity(feedRepository.findByFeedUuid(feedUuid)
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND)),
            feedHashtagRepository.findByFeedUuid(feedUuid).orElse(new FeedHashtag()),
            feedMediaRepository.findByFeedUuid(feedUuid));
    }

    @Transactional
    @Override
    public void updateFeed(UpdateFeedRequestDto requestDto) {

        feedRepository.save(
            requestDto.toFeedEntity(feedRepository.findByFeedUuid(requestDto.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
        feedKafkaProducer.send("feed-updated", requestDto.toEventEntity());
    }

    @Transactional
    @Override
    public void updateFeedStatus(UpdateFeedStatusRequestDto requestDto) {

        feedRepository.save(
            requestDto.toFeedEntity(feedRepository.findByFeedUuid(requestDto.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
        feedKafkaProducer.send("feed-status-updated", requestDto.toEventEntity());
    }

    @Transactional
    @Override
    public void updateFeedHashtag(UpdateFeedHashtagRequestDto requestDto) {

        feedRepository.save(
            requestDto.toFeedEntity(feedRepository.findByFeedUuid(requestDto.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
        feedHashtagRepository.save(requestDto.toFeedHashtagEntity());
        feedKafkaProducer.send("feed-hashtag-updated", requestDto.toEventEntity());
    }

    @Transactional
    @Override
    public void deleteFeed(String feedUuid) {

        feedRepository.delete(feedRepository.findByFeedUuid(feedUuid)
            .orElseThrow(() -> new BaseException(FEED_FORBIDDEN)));
        feedHashtagRepository.deleteAllByFeedUuid(feedUuid);
        feedMediaRepository.deleteByFeedUuid(feedUuid);
        feedKafkaProducer.send("feed-deleted", new DeleteFeedEvent(feedUuid));
    }

}
