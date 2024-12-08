package com.mulmeong.shorts.read.common.config;

import com.mulmeong.shorts.read.api.domain.event.ShortsCreateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsDeleteEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsHashtagUpdateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsInfoUpdateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsStatusUpdateEvent;
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
import org.springframework.kafka.support.serializer.JsonDeserializer;

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
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCreateEvent> shortsCreateEventListener() {
        return kafkaListenerContainerFactory(ShortsCreateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsDeleteEvent> shortsDeleteEventListener() {
        return kafkaListenerContainerFactory(ShortsDeleteEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsHashtagUpdateEvent>
        shortsHashtagUpdateEventListener() {

        return kafkaListenerContainerFactory(ShortsHashtagUpdateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsStatusUpdateEvent> shortsStatusUpdateEventListener() {
        return kafkaListenerContainerFactory(ShortsStatusUpdateEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsInfoUpdateEvent> shortsInfoUpdateEventListener() {
        return kafkaListenerContainerFactory(ShortsInfoUpdateEvent.class);
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
            JsonDeserializer.TRUSTED_PACKAGES, "com.mulmeong.shorts.read.api.domain.event"
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
        return factory;
    }

}
