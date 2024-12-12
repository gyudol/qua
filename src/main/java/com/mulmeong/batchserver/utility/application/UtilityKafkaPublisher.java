package com.mulmeong.batchserver.utility.application;

import com.mulmeong.event.utility.produce.FeedCreatedFollowersEvent;
import com.mulmeong.event.utility.produce.ShortsCreatedFollowersEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UtilityKafkaPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${event.utility.pub.topics.feed-create-followers.name}")
    private String feedCreatedFollowersEventTopic;
    @Value("${event.utility.pub.topics.shorts-create-followers.name}")
    private String shortsCreatedFollowersEventTopic;

    public void send(FeedCreatedFollowersEvent event) {
        kafkaTemplate.send(feedCreatedFollowersEventTopic, event);
        log.info("feed create topic: {}", feedCreatedFollowersEventTopic);
    }

    public void send(ShortsCreatedFollowersEvent event) {
        kafkaTemplate.send(shortsCreatedFollowersEventTopic, event);
        log.info("feed create topic: {}", shortsCreatedFollowersEventTopic);
    }
}
