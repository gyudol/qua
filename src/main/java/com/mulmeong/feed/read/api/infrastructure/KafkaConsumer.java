package com.mulmeong.feed.read.api.infrastructure;

import com.mulmeong.feed.read.api.application.FeedEventHandlerService;
import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedDeleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final FeedEventHandlerService feedEventHandlerService;

    @KafkaListener(topics = "feed-created", groupId = "feed-read-group", containerFactory = "feedCreateEventListener")
    public void consumeFeedCreateEvent(FeedCreateEvent event) {

        log.info("Consumed feed-created event: {}", event);
        feedEventHandlerService.createFeedFromEvent(event);
        log.info("Successfully processed feed-created event: {}", event);
    }

    @KafkaListener(topics = "feed-deleted", groupId = "feed-read-group", containerFactory = "feedDeleteEventListener")
    public void consumeFeedDeleteEvent(FeedDeleteEvent event) {

        log.info("Consumed feed-deleted event: {}", event);
        feedEventHandlerService.deleteFeedFromEvent(event);
        log.info("Successfully processed feed-deleted event: {}", event);
    }

}
