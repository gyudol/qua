package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.MemberBadgeUpdateEvent;
import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;

public interface MemberEventService {

    void createMember(MemberCreateEvent memberCreateEvent);

    void updateNickname(MemberNicknameUpdateEvent memberNicknameUpdateEvent);

    void updateProfileImage(MemberProfileImgUpdateEvent memberProfileImgUpdateEvent);

    void updateEquippedBadge(MemberBadgeUpdateEvent memberBadgeUpdateEvent);
}
