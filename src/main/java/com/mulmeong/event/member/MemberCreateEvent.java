package com.mulmeong.event.member;

import com.mulmeong.member.read.member.document.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateEvent {
    private String memberUuid;
    private String nickname;
    private String profileImageUrl;
    private LocalDateTime createdAt;

    public Member toEntity() {
        return Member.builder()
                .memberUuid(memberUuid)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .createdAt(createdAt)
                .grade("default") //todo : RewardService에서 계산한 등급으로 변경
                .followerCount(0)
                .followingCount(0)
                .feedCount(0)
                .shortsCount(0)
                .build();
    }
}
