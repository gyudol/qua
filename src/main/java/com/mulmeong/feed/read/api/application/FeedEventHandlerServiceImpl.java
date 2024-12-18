package com.mulmeong.feed.read.api.application;

import static com.mulmeong.feed.read.common.response.BaseResponseStatus.ELASTICSEARCH_DATA_MISMATCH;
import static com.mulmeong.feed.read.common.response.BaseResponseStatus.FEED_NOT_FOUND;
import static com.mulmeong.feed.read.common.response.BaseResponseStatus.HASHTAG_DUPLICATE_KEY;

import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedDeleteEvent;
import com.mulmeong.feed.read.api.domain.event.FeedHashtagUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedStatusUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedUpdateEvent;
import com.mulmeong.feed.read.api.infrastructure.ElasticFeedRepository;
import com.mulmeong.feed.read.api.infrastructure.FeedEventRepository;
import com.mulmeong.feed.read.api.infrastructure.HashtagEventRepository;
import com.mulmeong.feed.read.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class FeedEventHandlerServiceImpl implements FeedEventHandlerService {

    private final FeedEventRepository feedEventRepository;
    private final HashtagEventRepository hashtagEventRepository;
    private final ElasticFeedRepository elasticFeedRepository;

    @Override
    public void createFeedFromEvent(FeedCreateEvent event) {

        feedEventRepository.save(event.toFeedDocument());
        elasticFeedRepository.save(event.toElasticDocument());
        event.toHashtagEntities().forEach(hashtag -> {
            try {
                hashtagEventRepository.save(hashtag);
            } catch (DataIntegrityViolationException e) {
                log.info("{}: {}", hashtag.getName(), HASHTAG_DUPLICATE_KEY.getMessage());
            }
        });
    }

    @Override
    public void updateFeedHashtagFromEvent(FeedHashtagUpdateEvent event) {

        feedEventRepository.save(
            event.toFeedDocument(feedEventRepository.findByFeedUuid(event.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
        elasticFeedRepository.save(event.toElasticDocument(
            elasticFeedRepository.findByFeedUuid(event.getFeedUuid())
                .orElseThrow(() -> new BaseException(ELASTICSEARCH_DATA_MISMATCH))));
    }

    @Override
    public void updateFeedStatusFromEvent(FeedStatusUpdateEvent event) {

        feedEventRepository.save(
            event.toDocument(feedEventRepository.findByFeedUuid(event.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
        elasticFeedRepository.save(event.toElasticDocument(
            elasticFeedRepository.findByFeedUuid(event.getFeedUuid())
                .orElseThrow(() -> new BaseException(ELASTICSEARCH_DATA_MISMATCH))));
    }

    @Override
    public void updateFeedFromEvent(FeedUpdateEvent event) {

        feedEventRepository.save(
            event.toDocument(feedEventRepository.findByFeedUuid(event.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
        elasticFeedRepository.save(event.toElasticDocument(
            elasticFeedRepository.findByFeedUuid(event.getFeedUuid())
                .orElseThrow(() -> new BaseException(ELASTICSEARCH_DATA_MISMATCH))));
    }

    @Override
    public void deleteFeedFromEvent(FeedDeleteEvent event) {

        feedEventRepository.deleteByFeedUuid(event.getFeedUuid());
        elasticFeedRepository.deleteByFeedUuid(event.getFeedUuid());
    }

}
