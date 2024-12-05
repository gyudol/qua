package com.mulmeong.batchserver.utility.application;

import com.mulmeong.event.utility.produce.FeedCreatedFollowersEvent;
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

    public void send(FeedCreatedFollowersEvent event) {
        kafkaTemplate.send(feedCreatedFollowersEventTopic, event);
        log.info("feed create topic: {}", feedCreatedFollowersEventTopic);
    }
}
