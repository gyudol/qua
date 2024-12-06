package com.mulmeong.utility.application;

import com.mulmeong.event.produce.DislikesRenewEvent;
import com.mulmeong.event.produce.LikesRenewEvent;
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

    @Value("${event.utility.pub.topics.like-create.name}")
    private String likeCreateEventTopic;
    @Value("${event.utility.pub.topics.dislike-create.name}")
    private String dislikeCreateEventTopic;


    public void send(String topic, Object event) {
        log.info("Publishing event: {}", event);
        kafkaTemplate.send(topic, event);
    }

    public void sendLikedEvent(LikesRenewEvent event) {
        kafkaTemplate.send(likeCreateEventTopic, event);
        log.info("like topic: {}", likeCreateEventTopic);
    }


    public void sendDislikedEvent(DislikesRenewEvent event) {
        kafkaTemplate.send(dislikeCreateEventTopic, event);
        log.info("dislike topic: {}", dislikeCreateEventTopic);
    }
}
