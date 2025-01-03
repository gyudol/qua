package com.mulmeong.member.read.member.document;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 회원 프로필 조회시 사용되는 데이터를 모아둔 Member Read Entity.
 *
 * @author SeongGwang Ju
 */
@Getter
@NoArgsConstructor
@Document(collection = "member")
public class Member {

    @Id
    private String memberUuid;
    private String nickname;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private Long gradeId;
    private Long equippedBadgeId;
    // todo : 화면에 따른 기타 집계 데이터 추가/수정 필요
    private Integer followerCount;
    private Integer followingCount;
    private Integer feedCount;
    private Integer shortsCount;

    @Builder
    public Member(String memberUuid, String nickname, String profileImageUrl, LocalDateTime createdAt,
                  Long gradeId, Long equippedBadgeId, Integer followerCount, Integer followingCount, Integer feedCount,
                  Integer shortsCount) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
        this.gradeId = gradeId;
        this.equippedBadgeId = equippedBadgeId;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.feedCount = feedCount;
        this.shortsCount = shortsCount;
    }
}
