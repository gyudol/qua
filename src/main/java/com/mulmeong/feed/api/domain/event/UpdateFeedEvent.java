package com.mulmeong.feed.api.domain.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateFeedEvent {

    private String feedUuid;
    private String title;
    private String content;
    private Long categoryId;

    @Builder
    public UpdateFeedEvent(String feedUuid, String title, String content, Long categoryId) {
        this.feedUuid = feedUuid;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }

}
