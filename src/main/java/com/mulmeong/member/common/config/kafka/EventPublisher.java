package com.mulmeong.member.common.config.kafka;

import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
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

    /**
     * send(토픽, 이벤트)로 이벤트를 pub할 수 있지만
     * 토픽을 지정하는 부분을 서비스의 비즈니스로직에서 분리하는것이 좋다고 판단해
     * 본 클래스에서 오버로딩하는 구조로 변경하였습니다.
     *
     * @author: 주성광
     */

    @Value("${event.member.pub.topics.member-create.name}")
    private String memberCreateEventTopic;

    @Value("${event.member.pub.topics.nickname-update.name}")
    private String nicknameUpdateEventTopic;

    @Value("${event.member.pub.topics.profile-img-update.name}")
    private String profileImgUpdateEventTopic;

    public void send(String topic, Object event) {
        log.info("이벤트 발행, topic: {}", topic);
        kafkaTemplate.send(topic, event);
    }

    public void send(MemberCreateEvent event) {
        kafkaTemplate.send(memberCreateEventTopic, event);
    }

    public void send(MemberNicknameUpdateEvent event) {
        kafkaTemplate.send(nicknameUpdateEventTopic, event);
    }

    public void send(MemberProfileImgUpdateEvent event) {
        kafkaTemplate.send(profileImgUpdateEventTopic, event);
    }
}
