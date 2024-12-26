package com.mulmeong.contest.read.common.config;

import com.mulmeong.contest.read.common.exception.BaseException;
import com.mulmeong.event.contest.consume.ContestPostCreateEvent;
import com.mulmeong.event.contest.consume.ContestVoteRecordEvent;
import com.mulmeong.event.contest.consume.ContestVoteUpdateEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public DefaultErrorHandler errorHandler() {
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                new FixedBackOff(1000L, 2)); // 1초 대기, 최대 2번 재시도
        errorHandler.addNotRetryableExceptions(BaseException.class);
        return errorHandler;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ContestPostCreateEvent> contestPostCreateListener() {
        return kafkaListenerContainerFactory(ContestPostCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ContestVoteUpdateEvent> contestVoteUpdateListener() {
        return kafkaListenerContainerFactory(ContestVoteUpdateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ContestVoteRecordEvent> contestVoteRecordListener() {
        return kafkaListenerContainerFactory(ContestVoteRecordEvent.class);
    }


    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType) {
        return new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                JsonDeserializer.VALUE_DEFAULT_TYPE, messageType.getName(),
                JsonDeserializer.TRUSTED_PACKAGES, "com.mulmeong.event.contest"
        ));
    }

    public <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(Class<T> messageType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(messageType));
        factory.getContainerProperties().setGroupId(groupId);
        return factory;
    }
}