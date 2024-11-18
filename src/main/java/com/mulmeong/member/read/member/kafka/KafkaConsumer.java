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

    @KafkaListener(topics = "member-created", groupId = "member-read",
            containerFactory = "memberCreateEventListener")
    public void handleMemberCreatedEvent(MemberCreateEvent event) {
        memberService.createMember(event);
    }

    @KafkaListener(topics = "nickname-updated", groupId = "member-read",
            containerFactory = "nicknameUpdateEventListener")
    public void handleNicknameUpdatedEvent(MemberNicknameUpdateEvent event) {
        memberService.updateNickname(event);
    }

    @KafkaListener(topics = "profile-img-updated", groupId = "member-read",
            containerFactory = "profileUpdateEventListener")
    public void handleProfileImgUpdatedEvent(MemberProfileImgUpdateEvent event) {
        memberService.updateProfileImage(event);
    }
}
