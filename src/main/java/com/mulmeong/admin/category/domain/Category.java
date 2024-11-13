package com.mulmeong.admin.category.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;


@Getter
@Entity
@NoArgsConstructor
public class Category {

    @Comment("카테고리 ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("카테고리 이름")
    @Column(nullable = false, unique = true, length = 20)
    private String categoryName;

    @Comment("기본 보기 방식")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ViewType viewType;

    @Builder
    public Category(Long id, String categoryName, ViewType viewType) {
        this.id = id;
        this.categoryName = categoryName;
        this.viewType = viewType;
    }
}

