package com.mulmeong.shorts.read.api.infrastructure;

import com.mulmeong.shorts.read.api.application.ShortsEventHandlerService;
import com.mulmeong.shorts.read.api.domain.event.ShortsCreateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsDeleteEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsHashtagUpdateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsInfoUpdateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsStatusUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final ShortsEventHandlerService shortsEventHandlerService;

    @KafkaListener(topics = "${event.shorts.pub.topics.shorts-create.name}",
        containerFactory = "shortsCreateEventListener")
    public void consumeShortsCreateEvent(ShortsCreateEvent event) {

        log.info("Consumed shorts-created event: {}", event);
        shortsEventHandlerService.createShortsFromEvent(event);
        log.info("Successfully processed shorts-created event: {}", event);
    }

    @KafkaListener(topics = "${event.shorts.pub.topics.shorts-delete.name}",
        containerFactory = "shortsDeleteEventListener")
    public void consumeShortsDeleteEvent(ShortsDeleteEvent event) {

        log.info("Consumed shorts-deleted event: {}", event);
        shortsEventHandlerService.deleteShortsFromEvent(event);
        log.info("Successfully processed shorts-deleted event: {}", event);
    }

    @KafkaListener(topics = "${event.shorts.pub.topics.shorts-hashtag-update.name}",
        containerFactory = "shortsHashtagUpdateEventListener")
    public void consumeShortsHashtagUpdateEvent(ShortsHashtagUpdateEvent event) {

        log.info("Consumed shorts-hashtag-updated event: {}", event);
        shortsEventHandlerService.updateShortsHashtagFromEvent(event);
        log.info("Successfully processed shorts-hashtag-updated event: {}", event);
    }

    @KafkaListener(topics = "${event.shorts.pub.topics.shorts-status-update.name}",
        containerFactory = "shortsStatusUpdateEventListener")
    public void consumeShortsStatusUpdateEvent(ShortsStatusUpdateEvent event) {

        log.info("Consumed shorts-status-updated event: {}", event);
        shortsEventHandlerService.updateShortsStatusFromEvent(event);
        log.info("Successfully processed shorts-status-updated event: {}", event);
    }

    @KafkaListener(topics = "${event.shorts.pub.topics.shorts-update.name}",
        containerFactory = "shortsInfoUpdateEventListener")
    public void consumeShortsInfoUpdateEvent(ShortsInfoUpdateEvent event) {

        log.info("Consumed shorts-updated event: {}", event);
        shortsEventHandlerService.updateShortsInfoFromEvent(event);
        log.info("Successfully processed shorts-updated event: {}", event);
    }

}
