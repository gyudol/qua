package com.mulmeong.comment.entity;

import com.mulmeong.comment.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ShortsComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 36)
    private String shortsUuid;
    @Column(nullable = false, length = 36)
    private String memberUuid;
    @Column(nullable = false, length = 36)
    private String commentUuid;
    @Column(length = 1000)
    private String content;
    private boolean isDeleted;

    @Builder
    public ShortsComment(
            Long id,
            String shortsUuid,
            String memberUuid,
            String commentUuid,
            String content,
            boolean isDeleted
    ) {
        this.id = id;
        this.shortsUuid = shortsUuid;
        this.memberUuid = memberUuid;
        this.commentUuid = commentUuid;
        this.content = content;
        this.isDeleted = isDeleted;
    }
}
