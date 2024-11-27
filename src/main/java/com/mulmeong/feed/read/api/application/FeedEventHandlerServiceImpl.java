package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedDeleteEvent;
import com.mulmeong.feed.read.api.infrastructure.FeedEventRepository;
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
    public void deleteFeedFromEvent(FeedDeleteEvent event) {

        feedEventRepository.deleteByFeedUuid(event.getFeedUuid());
    }

}
