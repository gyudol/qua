package com.mulmeong.batchserver.feed.domain.entity;


import com.mulmeong.batchserver.feed.domain.model.Visibility;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Feed {

    @Id
    private Long id;
    private String feedUuid;
    private String memberUuid;
    private String title;
    private String content;
    private String categoryName;
    private Visibility visibility;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Feed(Long id, String feedUuid, String memberUuid, String title, String content,
                String categoryName, Visibility visibility, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.id = id;
        this.feedUuid = feedUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
        this.visibility = visibility;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
