package com.mulmeong.feed.api.domain.event;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedUpdateEvent {

    private String feedUuid;
    private String title;
    private String content;
    private Long categoryId;
    private LocalDateTime updatedAt;

    @Builder
    public FeedUpdateEvent(String feedUuid, String title, String content, Long categoryId,
        LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.updatedAt = updatedAt;

    }

}
