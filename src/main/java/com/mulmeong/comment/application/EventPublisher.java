package com.mulmeong.comment.application;

import com.mulmeong.event.comment.*;
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

    @Value("${event.comment.pub.topics.feed-comment-create.name}")
    private String feedCommentCreateEventTopic;
    @Value("${event.comment.pub.topics.feed-comment-update.name}")
    private String feedCommentUpdateEventTopic;
    @Value("${event.comment.pub.topics.feed-comment-delete.name}")
    private String feedCommentDeleteEventTopic;

    @Value("${event.comment.pub.topics.feed-recomment-create.name}")
    private String feedRecommentCreateEventTopic;
    @Value("${event.comment.pub.topics.feed-recomment-update.name}")
    private String feedRecommentUpdateEventTopic;
    @Value("${event.comment.pub.topics.feed-recomment-delete.name}")
    private String feedRecommentDeleteEventTopic;

    @Value("${event.comment.pub.topics.shorts-comment-create.name}")
    private String shortsCommentCreateEventTopic;
    @Value("${event.comment.pub.topics.shorts-comment-update.name}")
    private String shortsCommentUpdateEventTopic;
    @Value("${event.comment.pub.topics.shorts-comment-delete.name}")
    private String shortsCommentDeleteEventTopic;

    @Value("${event.comment.pub.topics.shorts-recomment-create.name}")
    private String shortsRecommentCreateEventTopic;
    @Value("${event.comment.pub.topics.shorts-recomment-update.name}")
    private String shortsRecommentUpdateEventTopic;
    @Value("${event.comment.pub.topics.shorts-recomment-delete.name}")
    private String shortsRecommentDeleteEventTopic;

    public void send(String topic, Object event) {
        log.info("Publishing event: {}", event);
        kafkaTemplate.send(topic, event);
    }

    public void send(FeedCommentCreateEvent event) {
        kafkaTemplate.send(feedCommentCreateEventTopic, event);
    }

    public void send(FeedCommentUpdateEvent event) {
        kafkaTemplate.send(feedCommentUpdateEventTopic, event);
    }

    public void send(FeedCommentDeleteEvent event) {
        kafkaTemplate.send(feedCommentDeleteEventTopic, event);
    }

    public void send(FeedRecommentCreateEvent event) {
        kafkaTemplate.send(feedRecommentCreateEventTopic, event);
    }

    public void send(FeedRecommentUpdateEvent event) {
        kafkaTemplate.send(feedRecommentUpdateEventTopic, event);
    }

    public void send(FeedRecommentDeleteEvent event) {
        kafkaTemplate.send(feedRecommentDeleteEventTopic, event);
    }

    public void send(ShortsCommentCreateEvent event) {
        kafkaTemplate.send(shortsCommentCreateEventTopic, event);
    }

    public void send(ShortsCommentUpdateEvent event) {
        kafkaTemplate.send(shortsCommentUpdateEventTopic, event);
    }

    public void send(ShortsCommentDeleteEvent event) {
        kafkaTemplate.send(shortsCommentDeleteEventTopic, event);
    }

    public void send(ShortsRecommentCreateEvent event) {
        kafkaTemplate.send(shortsRecommentCreateEventTopic, event);
    }

    public void send(ShortsRecommentUpdateEvent event) {
        kafkaTemplate.send(shortsRecommentUpdateEventTopic, event);
    }

    public void send(ShortsRecommentDeleteEvent event) {
        kafkaTemplate.send(shortsRecommentDeleteEventTopic, event);
    }
}
