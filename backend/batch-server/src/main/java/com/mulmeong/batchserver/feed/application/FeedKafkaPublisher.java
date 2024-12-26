package com.mulmeong.batchserver.feed.application;

import com.mulmeong.event.utility.produce.FeedMetricsUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FeedKafkaPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${event.feed.pub.topics.feed-metrics-update.name}")
    private String feedMetricsUpdateEventTopic;

    public void send(FeedMetricsUpdateEvent event) {
        kafkaTemplate.send(feedMetricsUpdateEventTopic, event);
        log.info("feed stats topic: {}", feedMetricsUpdateEventTopic);
    }
}
