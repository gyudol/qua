package com.mulmeong.feed.api.domain.event;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateFeedEvent {

    private String feedUuid;
    private String title;
    private String content;
    private Long categoryId;
    private LocalDateTime updatedAt;

    @Builder
    public UpdateFeedEvent(String feedUuid, String title, String content, Long categoryId,
        LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.updatedAt = updatedAt;

    }

}
