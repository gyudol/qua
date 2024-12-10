package com.mulmeong.feed.api.application;

import static com.mulmeong.feed.common.response.BaseResponseStatus.FEED_FORBIDDEN;
import static com.mulmeong.feed.common.response.BaseResponseStatus.FEED_NOT_FOUND;

import com.mulmeong.feed.api.domain.event.FeedDeleteEvent;
import com.mulmeong.feed.api.dto.in.FeedCreateDto;
import com.mulmeong.feed.api.dto.in.FeedHashtagUpdateDto;
import com.mulmeong.feed.api.dto.in.FeedUpdateDto;
import com.mulmeong.feed.api.dto.in.FeedStatusUpdateDto;
import com.mulmeong.feed.api.infrastructure.FeedHashtagRepository;
import com.mulmeong.feed.api.infrastructure.KafkaProducer;
import com.mulmeong.feed.api.infrastructure.FeedMediaRepository;
import com.mulmeong.feed.api.infrastructure.FeedRepository;
import com.mulmeong.feed.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final FeedMediaRepository feedMediaRepository;
    private final FeedHashtagRepository feedHashtagRepository;
    private final KafkaProducer kafkaProducer;

    @Override
    public void createFeed(FeedCreateDto requestDto) {

        feedRepository.save(requestDto.toFeedEntity());
        feedMediaRepository.saveAll(requestDto.toFeedMediaEntities());
        feedHashtagRepository.save(requestDto.toFeedHashtagEntity());
        kafkaProducer.send(requestDto.toEventEntity());
    }

    @Override
    public void updateFeed(FeedUpdateDto requestDto) {

        feedRepository.save(
            requestDto.toFeedEntity(feedRepository.findByFeedUuid(requestDto.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
        kafkaProducer.send(requestDto.toEventEntity());
    }

    @Override
    public void updateFeedStatus(FeedStatusUpdateDto requestDto) {

        feedRepository.save(
            requestDto.toFeedEntity(feedRepository.findByFeedUuid(requestDto.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
        kafkaProducer.send(requestDto.toEventEntity());
    }

    @Override
    public void updateFeedHashtag(FeedHashtagUpdateDto requestDto) {

        feedRepository.save(
            requestDto.toFeedEntity(feedRepository.findByFeedUuid(requestDto.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
        feedHashtagRepository.save(requestDto.toFeedHashtagEntity());
        kafkaProducer.send(requestDto.toEventEntity());
    }

    @Override
    public void deleteFeed(String feedUuid) {

        final String memberUuid = feedRepository.findByFeedUuid(feedUuid)
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND)).getMemberUuid();

        feedRepository.deleteByFeedUuid(feedUuid);
        feedHashtagRepository.deleteById(feedUuid);
        feedMediaRepository.deleteAllByFeedUuid(feedUuid);
        kafkaProducer.send(new FeedDeleteEvent(feedUuid, memberUuid));
    }

}
