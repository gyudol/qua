package com.mulmeong.contest.application;

import com.mulmeong.event.contest.produce.ContestPostCreateEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${event.contest.pub.topics.contest-post-create.name}")
    private String contestPostCreateEventTopic;

    public void send(String topic, Object event) {
        log.info("Publishing event: {}", event);
        kafkaTemplate.send(topic, event);
    }

    public void send(ContestPostCreateEvent event) {
        kafkaTemplate.send(contestPostCreateEventTopic, event);
        log.info("topic: {}", contestPostCreateEventTopic);
    }


}
