package com.mulmeong.comment.entity;

import com.mulmeong.comment.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class FeedComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 36)
    private String feedUuid;
    @Column(nullable = false, length = 36)
    private String memberUuid;
    @Column(nullable = false, length = 36)
    private String commentUuid;
    @Column(length = 1000)
    private String content;
    private Boolean status;

    @Builder
    public FeedComment(
            Long id,
            String feedUuid,
            String memberUuid,
            String commentUuid,
            String content,
            Boolean status
    ) {

        this.id = id;
        this.feedUuid = feedUuid;
        this.memberUuid = memberUuid;
        this.commentUuid = commentUuid;
        this.content = content;
        this.status = status;
    }
}
