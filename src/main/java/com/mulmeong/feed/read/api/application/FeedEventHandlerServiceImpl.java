package com.mulmeong.feed.read.api.application;

import static com.mulmeong.feed.read.common.response.BaseResponseStatus.ELASTICSEARCH_DATA_MISMATCH;
import static com.mulmeong.feed.read.common.response.BaseResponseStatus.FEED_NOT_FOUND;
import static com.mulmeong.feed.read.common.response.BaseResponseStatus.HASHTAG_DUPLICATE_KEY;
import static java.util.stream.Collectors.toList;

import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedDeleteEvent;
import com.mulmeong.feed.read.api.domain.event.FeedHashtagUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedMetricsUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedStatusUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedUpdateEvent;
import com.mulmeong.feed.read.api.domain.model.MediaType;
import com.mulmeong.feed.read.api.infrastructure.ElasticFeedRepository;
import com.mulmeong.feed.read.api.infrastructure.FeedEventRepository;
import com.mulmeong.feed.read.api.infrastructure.HashtagEventRepository;
import com.mulmeong.feed.read.common.exception.BaseException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
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

        Optional.ofNullable(event.getMediaList())
            .ifPresent(mediaList -> {
                List<CompletableFuture<Void>> futures = mediaList.stream()
                    .map(media -> CompletableFuture.runAsync(() -> {
                        try {
                            if (media.getMediaType() == MediaType.VIDEO) {
                                log.info("Processing media asynchronously with a delay of 10 seconds");
                                TimeUnit.SECONDS.sleep(10);
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            log.error("Delay interrupted: {}", e.getMessage());
                        }
                    }))
                    .toList();

                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenRun(() -> {
                        log.info("All media processing completed. Proceeding with save operations.");

                        feedEventRepository.save(event.toFeedDocument());
                        elasticFeedRepository.save(event.toElasticDocument());
                        event.toHashtagEntities().forEach(hashtag -> {
                            try {
                                hashtagEventRepository.save(hashtag);
                            } catch (DataIntegrityViolationException e) {
                                log.info("{}: {}", hashtag.getName(), HASHTAG_DUPLICATE_KEY.getMessage());
                            }
                        });
                    })
                    .exceptionally(e -> {
                        log.error("Error occurred while processing media: {}", e.getMessage());
                        return null;
                    });
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
    public void updateFeedMetricsFromEvent(FeedMetricsUpdateEvent event) {

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
