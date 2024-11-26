package com.mulmeong.feed.read.common.config;

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

    // TODO: 이벤트 리스너 정의

    /**
     * 특정 메시지 타입에 맞게 ConsumerFactory를 생성합니다.
     *
     * @param messageType 제네릭으로 선언한 Event 객체
     * @return DefaultKafkaConsumerFactory, Kafka Listener가 사용하는 기본 Consumer Factory
     */
    @Bean
    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType) {
        return new DefaultKafkaConsumerFactory<>(Map.of(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
            JsonDeserializer.TRUSTED_PACKAGES, "com.mulmeong.event",
            JsonDeserializer.VALUE_DEFAULT_TYPE, messageType
        ));
    }

    /**
     * 컨테이너 팩토리는 다중 스레드에서 Kafka 메시지를 처리하며, 이 설정을 통해 KafkaListener가 메시지를 소비합니다.
     *
     * @param messageType 제네릭으로 선언한 Event 객체
     * @return ConcurrentKafkaListenerContainerFactory, 다중 스레드에서 Kafka 메시지를 처리하는 컨테이너 팩토리
     */
    @Bean
    public <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(Class<T> messageType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(messageType));
        return factory;
    }

}
