package com.mulmeong.member.read.member.dto.out;

import com.mulmeong.member.read.member.document.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfileDto {
    // Member Document에 있는 모든 필드

    private String memberUuid;
    private String nickname;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private Long gradeId;
    private Long equippedBadgeId;
    private Integer followerCount;
    private Integer followingCount;
    private Integer feedCount;
    private Integer shortsCount;

    public static MemberProfileDto fromDocument(Member member) {
        return MemberProfileDto.builder()
                .memberUuid(member.getMemberUuid())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .createdAt(member.getCreatedAt())
                .gradeId(member.getGradeId())
                .equippedBadgeId(member.getEquippedBadgeId())
                .followerCount(member.getFollowerCount())
                .followingCount(member.getFollowingCount())
                .feedCount(member.getFeedCount())
                .shortsCount(member.getShortsCount())
                .build();
    }
}
