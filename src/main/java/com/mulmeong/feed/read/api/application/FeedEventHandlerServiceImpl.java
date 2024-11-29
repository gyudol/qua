package com.mulmeong.feed.read.api.application;

import static com.mulmeong.feed.read.common.response.BaseResponseStatus.FEED_NOT_FOUND;

import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedDeleteEvent;
import com.mulmeong.feed.read.api.domain.event.FeedHashtagUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedStatusUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedUpdateEvent;
import com.mulmeong.feed.read.api.infrastructure.FeedEventRepository;
import com.mulmeong.feed.read.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedEventHandlerServiceImpl implements FeedEventHandlerService {

    private final FeedEventRepository feedEventRepository;

    @Override
    public void createFeedFromEvent(FeedCreateEvent event) {

        feedEventRepository.save(event.toDocument());
    }

    @Override
    public void updateFeedHashtagFromEvent(FeedHashtagUpdateEvent event) {

        feedEventRepository.save(
            event.toDocument(feedEventRepository.findByFeedUuid(event.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
    }

    @Override
    public void updateFeedStatusFromEvent(FeedStatusUpdateEvent event) {

        feedEventRepository.save(
            event.toDocument(feedEventRepository.findByFeedUuid(event.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
    }

    @Override
    public void updateFeedFromEvent(FeedUpdateEvent event) {

        feedEventRepository.save(
            event.toDocument(feedEventRepository.findByFeedUuid(event.getFeedUuid())
                .orElseThrow(() -> new BaseException(FEED_NOT_FOUND))));
    }

    @Override
    public void deleteFeedFromEvent(FeedDeleteEvent event) {

        feedEventRepository.deleteByFeedUuid(event.getFeedUuid());
    }

}
