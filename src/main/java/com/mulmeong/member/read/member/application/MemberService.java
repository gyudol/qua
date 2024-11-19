package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
import com.mulmeong.member.read.member.dto.out.MemberProfileDto;

public interface MemberService {

    void createMember(MemberCreateEvent memberCreateEvent);

    void updateNickname(MemberNicknameUpdateEvent memberNicknameUpdateEvent);

    void updateProfileImage(MemberProfileImgUpdateEvent memberProfileImgUpdateEvent);

    MemberProfileDto getMemberProfile(String nickname);
}
