package com.mulmeong.member.read.common.config;

import com.mulmeong.event.member.*;
import com.mulmeong.member.read.common.exception.BaseException;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Map;

/**
 * Kafka Consumer 설정 클래스.
 * Generic 타입을 사용해 Boilerplate 코드를 최대한 줄이도록 하였습니다.
 *
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;


    /**
     * 아래 코드들은 이벤트가 생성될 때 마다 작성해야 하는 코드입니다.
     * 특정 이벤트의 메시지를 소비하는 Kafka Listener 컨테이너 팩토리를 생성합니다.
     *
     * @return {@link KafkaListenerContainerFactory} Kafka Listener가 사용하는 기본 Consumer Factory
     */
    /* 회원가입 이벤트 리스너 */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberCreateEvent> memberCreateEventListener() {
        return kafkaListenerContainerFactory(MemberCreateEvent.class);
    }

    /* 닉네임 수정 이벤트 리스너 */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberNicknameUpdateEvent> nicknameUpdateEventListener() {
        return kafkaListenerContainerFactory(MemberNicknameUpdateEvent.class);
    }

    /* 프로필사진 수정 이벤트 리스너 */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberProfileImgUpdateEvent> profileUpdateEventListener() {
        return kafkaListenerContainerFactory(MemberProfileImgUpdateEvent.class);
    }

    /* 멤버의 뱃지 수정 이벤트 리스너 */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberBadgeUpdateEvent> memberBadgeUpdateEventListener() {
        return kafkaListenerContainerFactory(MemberBadgeUpdateEvent.class);
    }

    /* 멤버 등급 수정 이벤트 리스너 */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberGradeUpdateEvent> memberGradeUpdateEventListener() {
        return kafkaListenerContainerFactory(MemberGradeUpdateEvent.class);
    }



    // =================================================================
    // 아래는 공통 설정입니다.

    /**
     * 특정 메시지 타입에 맞게 ConsumerFactory를 생성합니다.
     *
     * @param messageType 제네릭으로 선언한 Event 객체
     * @return DefaultKafkaConsumerFactory, Kafka Listener가 사용하는 기본 Consumer Factory
     */
    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType) {
        return new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer,
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

    /**
     * Kafka Listener에서 예외가 발생했을 때 처리하는 DefaultErrorHandler를 생성.
     * 재시도 횟수와 대기 시간을 설정합니다.
     * BaseException은 재시도하지 않습니다.
     *
     * @return DefaultErrorHandler DefaultErrorHandler 객체
     */
    @Bean
    public DefaultErrorHandler errorHandler() {
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                new FixedBackOff(1000L, 2)); // 1초 대기, 최대 2번 재시도
        errorHandler.addNotRetryableExceptions(BaseException.class);
        return errorHandler;
    }
}
