package com.mulmeong.member.interest.hashtag.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"memberUuid", "hashtagName"})})
@Entity
public class InterestHashtag {

    @Comment("ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 36)
    private String memberUuid;

    @Comment("회원의 관심 해시태그 이름")
    @Column(nullable = false, length = 50)
    private String hashtagName;

    @Builder
    public InterestHashtag(Long id, String memberUuid, String hashtagName) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.hashtagName = hashtagName;
    }
}
