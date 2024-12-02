package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.api.domain.event.FeedDeleteEvent;
import com.mulmeong.feed.api.domain.event.FeedHashtagUpdateEvent;
import com.mulmeong.feed.api.domain.event.FeedStatusUpdateEvent;
import com.mulmeong.feed.api.domain.event.FeedUpdateEvent;
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

    @Value("${event.feed.pub.topics.feed-create.name}")
    private String feedCreateEventTopic;

    @Value("${event.feed.pub.topics.feed-delete.name}")
    private String feedDeleteEventTopic;

    @Value("${event.feed.pub.topics.feed-update.name}")
    private String feedUpdateEventTopic;

    @Value("${event.feed.pub.topics.feed-hashtag-update.name}")
    private String feedHashtagUpdateEventTopic;

    @Value("${event.feed.pub.topics.feed-status-update.name}")
    private String feedStatusUpdateEventTopic;

    //    public void send(String topic, Object event) {
    //        log.info("Sending FeedEvent: {}, topic: {}", event, topic);
    //        kafkaTemplate.send(topic, event);
    //    }`

    public void send(FeedCreateEvent event) {
        log.info("Sending FeedCreateEvent: {}, topic: {}", event, feedCreateEventTopic);
        kafkaTemplate.send(feedCreateEventTopic, event);
    }

    public void send(FeedDeleteEvent event) {
        log.info("Sending FeedDeleteEvent: {}, topic: {}", event, feedDeleteEventTopic);
        kafkaTemplate.send(feedDeleteEventTopic, event);
    }

    public void send(FeedUpdateEvent event) {
        log.info("Sending FeedUpdateEvent: {}, topic: {}", event, feedUpdateEventTopic);
        kafkaTemplate.send(feedUpdateEventTopic, event);
    }

    public void send(FeedHashtagUpdateEvent event) {
        log.info("Sending FeedHashtagUpdateEvent: {}, topic: {}", event,
            feedHashtagUpdateEventTopic);
        kafkaTemplate.send(feedHashtagUpdateEventTopic, event);
    }

    public void send(FeedStatusUpdateEvent event) {
        log.info("Sending FeedStatusUpdateEvent: {}, topic: {}", event, feedStatusUpdateEventTopic);
        kafkaTemplate.send(feedStatusUpdateEventTopic, event);
    }

}
