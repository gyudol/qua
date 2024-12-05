package com.mulmeong.member.read.member.infrastructure;

import com.mulmeong.event.member.MemberBadgeUpdateEvent;
import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
import com.mulmeong.member.read.member.application.MemberEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final MemberEventService memberService;

    @KafkaListener(topics = "${event.member.pub.topics.member-create.name}",
            containerFactory = "memberCreateEventListener")
    public void handleMemberCreatedEvent(MemberCreateEvent event) {
        log.info("Consumed 회원 생성 이벤트 : {}", event);
        memberService.createMember(event);
    }

    @KafkaListener(topics = "${event.member.pub.topics.nickname-update.name}",
            containerFactory = "nicknameUpdateEventListener")
    public void handleNicknameUpdatedEvent(MemberNicknameUpdateEvent event) {
        log.info("Consumed 회원 닉네임 변경 이벤트 : {}", event);
        memberService.updateNickname(event);
    }

    @KafkaListener(topics = "${event.member.pub.topics.profile-img-update.name}",
            containerFactory = "profileUpdateEventListener")
    public void handleProfileImgUpdatedEvent(MemberProfileImgUpdateEvent event) {
        log.info("Consumed 프로필 이미지 변경 이벤트 : {}", event);
        memberService.updateProfileImage(event);
    }

    @KafkaListener(topics = "${event.badge.pub.topics.member-badge-update.name}",
            containerFactory = "memberBadgeUpdateEventListener")
    public void handleMemberBadgeUpdatedEvent(MemberBadgeUpdateEvent event) {
        log.info("Consumed 회원 뱃지 변경 이벤트 : {}", event);
        memberService.updateEquippedBadge(event);
    }
}
