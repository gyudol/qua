package com.mulmeong.member.auth.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@ToString
@DynamicInsert
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"oauthId", "oauthProvider"})})
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 36, unique = true)
    private String memberUuid;

    @Comment("소셜 식별자")
    @Column(nullable = false, length = 255)
    private String oauthId;

    @Comment("소셜 제공자")
    @Column(nullable = false, length = 50)
    private String oauthProvider;

    @Comment("회원 이메일")
    @Column(length = 320)
    private String email;

    @Comment("회원 닉네임")
    @Column(nullable = false, length = 15, unique = true)
    private String nickname;

    @Comment("가입 일시")
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Comment("프로필 이미지 URL")
    @Column(length = 2083)
    private String profileImageUrl;

    @Builder
    public Member(Long id, String memberUuid, String oauthId, String oauthProvider,
                  String email, String nickname, LocalDateTime createdAt, String profileImageUrl) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.oauthId = oauthId;
        this.oauthProvider = oauthProvider;
        this.email = email;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.profileImageUrl = profileImageUrl;
    }
}
