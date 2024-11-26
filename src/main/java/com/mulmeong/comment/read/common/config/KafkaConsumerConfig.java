package com.mulmeong.comment.read.common.config;

import java.util.HashMap;
import java.util.Map;

import com.mulmeong.event.comment.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCommentCreateEvent> feedCommentCreateListener() {
        return kafkaListenerContainerFactory(FeedCommentCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCommentUpdateEvent> feedCommentUpdateListener() {
        return kafkaListenerContainerFactory(FeedCommentUpdateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCommentDeleteEvent> feedCommentDeleteListener() {
        return kafkaListenerContainerFactory(FeedCommentDeleteEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedRecommentCreateEvent> feedRecommentCreateListener() {
        return kafkaListenerContainerFactory(FeedRecommentCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedRecommentUpdateEvent> feedRecommentUpdateListener() {
        return kafkaListenerContainerFactory(FeedRecommentUpdateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedRecommentDeleteEvent> feedRecommentDeleteListener() {
        return kafkaListenerContainerFactory(FeedRecommentDeleteEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCommentCreateEvent> shortsCommentCreateListener() {
        return kafkaListenerContainerFactory(ShortsCommentCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCommentUpdateEvent> shortsCommentUpdateListener() {
        return kafkaListenerContainerFactory(ShortsCommentUpdateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCommentDeleteEvent> shortsCommentDeleteListener() {
        return kafkaListenerContainerFactory(ShortsCommentDeleteEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentCreateEvent> shortsRecommentCreateListener() {
        return kafkaListenerContainerFactory(ShortsRecommentCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentUpdateEvent> shortsRecommentUpdateListener() {
        return kafkaListenerContainerFactory(ShortsRecommentUpdateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentDeleteEvent> shortsRecommentDeleteListener() {
        return kafkaListenerContainerFactory(ShortsRecommentDeleteEvent.class);
    }

    @Bean
    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.mulmeong.event.comment");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, messageType);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(Class<T> messageType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(messageType));
        return factory;
    }
}