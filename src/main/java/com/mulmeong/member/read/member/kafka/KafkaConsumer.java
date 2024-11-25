package com.mulmeong.member.read.member.kafka;

import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
import com.mulmeong.member.read.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final MemberService memberService;

    @KafkaListener(topics = "${event.member.pub.topics.member-create.name}",
            containerFactory = "memberCreateEventListener")
    public void handleMemberCreatedEvent(MemberCreateEvent event) {
        memberService.createMember(event);
    }

    @KafkaListener(topics = "${event.member.pub.topics.nickname-update.name}",
            containerFactory = "nicknameUpdateEventListener")
    public void handleNicknameUpdatedEvent(MemberNicknameUpdateEvent event) {
        memberService.updateNickname(event);
    }

    @KafkaListener(topics = "${event.member.pub.topics.profile-img-update.name}",
            containerFactory = "profileUpdateEventListener")
    public void handleProfileImgUpdatedEvent(MemberProfileImgUpdateEvent event) {
        log.info("Consumed 프로필 이미지 변경 이벤트 : {}", event);
        memberService.updateProfileImage(event);
    }
}
