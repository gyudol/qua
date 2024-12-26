package com.mulmeong.batchserver.common.config;


import com.mulmeong.batchserver.common.exception.BaseException;
import com.mulmeong.event.utility.consume.*;
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
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
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
    public ConcurrentKafkaListenerContainerFactory<String, FeedCreateEvent> feedCreateListener() {
        return kafkaListenerContainerFactory(FeedCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCreateEvent> shortsCreateListener() {
        return kafkaListenerContainerFactory(ShortsCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LikeRenewCreateEvent> likeRenewCreateListener() {
        return kafkaListenerContainerFactory(LikeRenewCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DislikeRenewCreateEvent> dislikeRenewCreateListener() {
        return kafkaListenerContainerFactory(DislikeRenewCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FollowCreateEvent> followCreateListener() {
        return kafkaListenerContainerFactory(FollowCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UnfollowEvent> unfollowListener() {
        return kafkaListenerContainerFactory(UnfollowEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCommentCreateEvent> feedCommentCreateListener() {
        return kafkaListenerContainerFactory(FeedCommentCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCommentCreateEvent> shortsCommentCreateListener() {
        return kafkaListenerContainerFactory(ShortsCommentCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCommentDeleteEvent> feedCommentDeleteListener() {
        return kafkaListenerContainerFactory(FeedCommentDeleteEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCommentDeleteEvent> shortsCommentDeleteListener() {
        return kafkaListenerContainerFactory(ShortsCommentDeleteEvent.class);
    }


    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType) {
        return new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ConsumerConfig.GROUP_ID_CONFIG, groupId,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class,
                ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class.getName(),
                ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName(),
                JsonDeserializer.VALUE_DEFAULT_TYPE, messageType.getName(),
                JsonDeserializer.TRUSTED_PACKAGES, "com.mulmeong.*"

        ));
    }

    /**
     * 위의 consumerFactory와 컨테이너 프로퍼티에 group-id 를 설정한 '컨테이너 팩토리'를 생성합니다.
     * 이 컨테이너 팩토리는 다중 스레드에서 Kafka 메시지를 처리하며, 이 설정을 통해 KafkaListener가 메시지를 소비합니다.
     *
     * @param messageType 제네릭으로 선언한 Event 객체
     * @return ConcurrentKafkaListenerContainerFactory, 다중 스레드에서 Kafka 메시지를 처리하는 컨테이너 팩토리
     */
    public <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(Class<T> messageType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(messageType));
        factory.getContainerProperties().setGroupId(groupId);
        return factory;
    }
}