package com.mulmeong.member.interest.category.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"memberUuid", "categoryId"})})
@Entity
public class InterestCategory {

    @Comment("ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 36)
    private String memberUuid;

    @Comment("회원의 관심 카테고리 ID")
    @Column(nullable = false)
    private Long categoryId;

    @Builder
    public InterestCategory(Long id, String memberUuid, Long categoryId) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.categoryId = categoryId;
    }
}
