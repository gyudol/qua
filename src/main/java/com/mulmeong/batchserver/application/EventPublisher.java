package com.mulmeong.batchserver.application;

import com.mulmeong.event.contest.produce.ContestVoteUpdateEvent;
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

    @Value("${event.contest.pub.topics.contest-vote-update.name}")
    private String contestVoteUpdateEventTopic;


    public void send(String topic, Object event) {
        log.info("Publishing event: {}", event);
        kafkaTemplate.send(topic, event);
    }

    public void send(ContestVoteUpdateEvent event) {
        kafkaTemplate.send(contestVoteUpdateEventTopic, event);
        log.info("topic: {}", contestVoteUpdateEventTopic);
    }

}
