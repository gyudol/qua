package com.mulmeong.comment.entity;

import com.mulmeong.comment.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ShortsRecomment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 36)
    private String commentUuid;
    @Column(nullable = false, length = 36)
    private String memberUuid;
    @Column(nullable = false, length = 36)
    private String recommentUuid;
    @Column(length = 1000)
    private String content;

    @Builder
    public ShortsRecomment(
            Long id,
            String commentUuid,
            String memberUuid,
            String recommentUuid,
            String content
    ) {
        this.id = id;
        this.commentUuid = commentUuid;
        this.memberUuid = memberUuid;
        this.recommentUuid = recommentUuid;
        this.content = content;
    }
}
