package com.mulmeong.member.read.common.config;

import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    /**
     * {@link CommonJsonDeserializer} 에서 JSON 데이터를 역직렬화하는 설정을 가져옵니다.
     *
     * @return Kafka 컨슈머 설정을 포함하는 Map
     */
    @Bean
    public Map<String, Object> readConsumerConfigs() {
        return CommonJsonDeserializer.getStringObjectMap(bootstrapServers, groupId);
    }

    /**
     * 아래 내용부터는 특정 이벤트의 메시지를 처리하기 위한 ConsumerFactory와 ListenerContainerFactory를 생성합니다.
     *
     * @return 특정 이벤트 타입을 수신하는 컨슈머 설정 Map
     */

    /* 회원가입 후 이벤트 리스너 */
    @Bean
    public ConsumerFactory<String, MemberCreateEvent> memberCreateEventConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberCreateEvent> memberCreateEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, MemberCreateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(memberCreateEventConsumerFactory());
        return factory;
    }

    /* 닉네임 수정 후 이벤트 리스너 */
    @Bean
    public ConsumerFactory<String, MemberNicknameUpdateEvent> nicknameUpdateEventConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberNicknameUpdateEvent> nicknameUpdateEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, MemberNicknameUpdateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(nicknameUpdateEventConsumerFactory());
        return factory;
    }

    /* 프로필사진 수정 후 이벤트 리스너 */
    @Bean
    public ConsumerFactory<String, MemberProfileImgUpdateEvent> profileUpdateEventConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberProfileImgUpdateEvent> profileUpdateEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, MemberProfileImgUpdateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(profileUpdateEventConsumerFactory());
        return factory;
    }
}
