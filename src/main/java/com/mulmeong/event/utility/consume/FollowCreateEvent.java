package com.mulmeong.event.utility.consume;

import com.mulmeong.batchserver.member.domain.document.MemberRead;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class FollowCreateEvent {

    private String sourceUuid;
    private String targetUuid;

    public MemberRead toFollowerUp(MemberRead member, int followerCnt) {
        return MemberRead.builder()
                .memberUuid(member.getMemberUuid())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .createdAt(member.getCreatedAt())
                .gradeId(member.getGradeId())
                .equippedBadgeId(member.getEquippedBadgeId())
                .followerCount(followerCnt)
                .followingCount(member.getFollowingCount())
                .feedCount(member.getFeedCount())
                .shortsCount(member.getShortsCount())
                .build();
    }

    public MemberRead toFollowingUp(MemberRead member, int followingCnt) {
        return MemberRead.builder()
                .memberUuid(member.getMemberUuid())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .createdAt(member.getCreatedAt())
                .gradeId(member.getGradeId())
                .equippedBadgeId(member.getEquippedBadgeId())
                .followerCount(member.getFollowerCount())
                .followingCount(followingCnt)
                .feedCount(member.getFeedCount())
                .shortsCount(member.getShortsCount())
                .build();
    }

}