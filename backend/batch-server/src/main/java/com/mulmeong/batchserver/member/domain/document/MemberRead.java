package com.mulmeong.batchserver.member.domain.document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Document(collection = "member")
public class MemberRead {

    @Id
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

    @Builder
    public MemberRead(String memberUuid, String nickname, String profileImageUrl, LocalDateTime createdAt,
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