package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.*;

public interface MemberEventService {

    void createMember(MemberCreateEvent memberCreateEvent);

    void updateNickname(MemberNicknameUpdateEvent memberNicknameUpdateEvent);

    void updateProfileImage(MemberProfileImgUpdateEvent memberProfileImgUpdateEvent);

    void updateEquippedBadge(MemberBadgeUpdateEvent memberBadgeUpdateEvent);

    void updateGrade(MemberGradeUpdateEvent memberGradeUpdateEvent);
}
