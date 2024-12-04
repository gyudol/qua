package com.mulmeong.shorts.api.infrastructure;

import com.mulmeong.shorts.api.domain.event.ShortsCreateEvent;
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

    public void send(ShortsCreateEvent event) {
        log.info("Sending ShortsCreateEvent: {}, topic: {}", event, shortsCreateEventTopic);
        kafkaTemplate.send(shortsCreateEventTopic, event);
    }

}
