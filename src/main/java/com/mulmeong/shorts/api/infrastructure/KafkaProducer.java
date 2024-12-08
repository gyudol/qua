package com.mulmeong.shorts.api.infrastructure;

import com.mulmeong.shorts.api.domain.event.ShortsCreateEvent;
import com.mulmeong.shorts.api.domain.event.ShortsInfoUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${event.shorts.pub.topics.shorts-create.name}")
    private String shortsCreateEventTopic;

    @Value("${event.shorts.pub.topics.shorts-delete.name")
    private String shortsDeleteEventTopic;

    @Value("${event.shorts.pub.topics.shorts-update.name}")
    private String shortsInfoUpdateEventTopic;

    @Value("${event.shorts.pub.topics.shorts-hashtag-update.name}")
    private String shortsHashtagUpdateEventTopic;

    @Value("${event.shorts.pub.topics.shorts-status-update.name}")
    private String shortsStatusUpdateEventTopic;

    public void send(ShortsCreateEvent event) {
        log.info("Sending ShortsCreateEvent: {}, topic: {}", event, shortsCreateEventTopic);
        kafkaTemplate.send(shortsCreateEventTopic, event);
    }

    public void send(ShortsInfoUpdateEvent event) {
        log.info("Sending ShortsInfoUpdateEvent: {}, topic: {}", event, shortsInfoUpdateEventTopic);
        kafkaTemplate.send(shortsInfoUpdateEventTopic, event);
    }

}
