package com.mulmeong.feed.read.common.config;

import com.mulmeong.feed.read.api.domain.event.FeedCreateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedDeleteEvent;
import com.mulmeong.feed.read.api.domain.event.FeedHashtagUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedStatusUpdateEvent;
import com.mulmeong.feed.read.api.domain.event.FeedUpdateEvent;
import com.mulmeong.feed.read.common.exception.BaseException;
import java.util.Map;
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

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    /**
     * 특정 이벤트의 메시지를 소비하는 Kafka Listener 컨테이너 팩토리를 생성.
     *
     * @return KafkaListenerContainerFactory, KafkaListener가 사용하는 기본 ConsumerFactory
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCreateEvent> feedCreateEventListener() {
        return kafkaListenerContainerFactory(FeedCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedDeleteEvent> feedDeleteEventListener() {
        return kafkaListenerContainerFactory(FeedDeleteEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedHashtagUpdateEvent> feedHashtagUpdateEventListener() {
        return kafkaListenerContainerFactory(FeedHashtagUpdateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedStatusUpdateEvent> feedStatusUpdateEventListener() {
        return kafkaListenerContainerFactory(FeedStatusUpdateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedUpdateEvent> feedUpdateEventListener() {
        return kafkaListenerContainerFactory(FeedUpdateEvent.class);
    }

    /**
     * KafkaListener에서 예외가 발생했을 때 처리하는 DefaultErrorHandler를 생성.
     * 재시도 횟수와 대기 시간을 설정
     *
     * @return DefaultErrorHandler DefaultErrorHandler 객체
     */
    @Bean
    public DefaultErrorHandler errorHandler() {
        DefaultErrorHandler handler = new DefaultErrorHandler(
            new FixedBackOff(1000L, 3));    // 1초 대기, 최대 3회 재시도

        handler.addNotRetryableExceptions(BaseException.class);

        return handler;
    }

    /**
     * 특정 메시지 타입에 맞게 ConsumerFactory 생성.
     *
     * @param messageType 제네릭으로 선언한 Event 객체
     * @return DefaultKafkaConsumerFactory, Kafka Listener가 사용하는 기본 Consumer Factory
     */
    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType) {
        return new DefaultKafkaConsumerFactory<>(Map.of(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG, groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
            JsonDeserializer.VALUE_DEFAULT_TYPE, messageType.getName(),
            JsonDeserializer.TRUSTED_PACKAGES, "com.mulmeong.feed.read.api.domain.event"
        ));
    }

    /**
     * 컨테이너 팩토리는 다중 스레드에서 Kafka 메시지를 처리하며, 이 설정을 통해 KafkaListener가 메시지를 소비.
     *
     * @param messageType 제네릭으로 선언한 Event 객체
     * @return ConcurrentKafkaListenerContainerFactory, 다중 스레드에서 Kafka 메시지를 처리하는 컨테이너 팩토리
     */
    public <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(
        Class<T> messageType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(messageType));
        factory.getContainerProperties().setGroupId(groupId);
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }

}
