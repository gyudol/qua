package com.mulmeong.utility.application;

import com.mulmeong.event.produce.DislikeRenewCreateEvent;
import com.mulmeong.event.produce.FollowCreateEvent;
import com.mulmeong.event.produce.LikeCreateEvent;
import com.mulmeong.event.produce.LikeRenewCreateEvent;
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
    @Value("${event.utility.pub.topics.like-renew-create.name}")
    private String likeRenewCreateEventTopic;
    @Value("${event.utility.pub.topics.dislike-renew-create.name}")
    private String dislikeRenewCreateEventTopic;
    @Value("${event.utility.pub.topics.follow-create.name}")
    private String followCreateEventTopic;


    public void send(String topic, Object event) {
        log.info("Publishing event: {}", event);
        kafkaTemplate.send(topic, event);
    }

    public void sendLikedEvent(LikeCreateEvent event) {
        kafkaTemplate.send(likeCreateEventTopic, event);
        log.info("like topic: {}", likeCreateEventTopic);
    }

    public void sendLikedRenewEvent(LikeRenewCreateEvent event) {
        kafkaTemplate.send(likeRenewCreateEventTopic, event);
        log.info("like topic: {}", likeRenewCreateEventTopic);
    }


    public void sendDislikedEvent(DislikeRenewCreateEvent event) {
        kafkaTemplate.send(dislikeRenewCreateEventTopic, event);
        log.info("dislike topic: {}", dislikeRenewCreateEventTopic);
    }

    public void sendFollowEvent(FollowCreateEvent event) {
        kafkaTemplate.send(followCreateEventTopic, event);
        log.info("dislike topic: {}", followCreateEventTopic);
    }
}
