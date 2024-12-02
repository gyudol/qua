package com.mulmeong.feed.read.api.infrastructure;

import com.mulmeong.feed.read.api.application.FeedEventHandlerService;
import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedDeleteEvent;
import com.mulmeong.feed.read.api.domain.event.FeedHashtagUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedStatusUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final FeedEventHandlerService feedEventHandlerService;

    @KafkaListener(topics = "${event.feed.pub.topics.feed-create.name}",
        containerFactory = "feedCreateEventListener")
    public void consumeFeedCreateEvent(FeedCreateEvent event) {

        log.info("Consumed feed-created event: {}", event);
        feedEventHandlerService.createFeedFromEvent(event);
        log.info("Successfully processed feed-created event: {}", event);
    }

    @KafkaListener(topics = "${event.feed.pub.topics.feed-delete.name}",
        containerFactory = "feedDeleteEventListener")
    public void consumeFeedDeleteEvent(FeedDeleteEvent event) {

        log.info("Consumed feed-deleted event: {}", event);
        feedEventHandlerService.deleteFeedFromEvent(event);
        log.info("Successfully processed feed-deleted event: {}", event);
    }

    @KafkaListener(topics = "${event.feed.pub.topics.feed-hashtag-update.name}",
        containerFactory = "feedHashtagUpdateEventListener")
    public void consumeFeedHashtagUpdateEvent(FeedHashtagUpdateEvent event) {

        log.info("Consumed feed-hashtag-updated event: {}", event);
        feedEventHandlerService.updateFeedHashtagFromEvent(event);
        log.info("Successfully processed feed-hashtag-updated event: {}", event);
    }

    @KafkaListener(topics = "${event.feed.pub.topics.feed-status-update.name}",
        containerFactory = "feedStatusUpdateEventListener")
    public void consumeFeedStatusUpdateEvent(FeedStatusUpdateEvent event) {

        log.info("Consumed feed-status-updated event: {}", event);
        feedEventHandlerService.updateFeedStatusFromEvent(event);
        log.info("Successfully processed feed-status-updated event: {}", event);
    }

    @KafkaListener(topics = "${event.feed.pub.topics.feed-update.name}",
        containerFactory = "feedUpdateEventListener")
    public void consumeFeedUpdateEvent(FeedUpdateEvent event) {

        log.info("Consumed feed-updated event: {}", event);
        feedEventHandlerService.updateFeedFromEvent(event);
        log.info("Successfully processed feed-updated event: {}", event);
    }

}
