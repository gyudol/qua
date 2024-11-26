package com.mulmeong.feed.api.domain.event;

import com.mulmeong.feed.api.domain.model.Visibility;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedStatusUpdateEvent {

    private String feedUuid;
    private Visibility visibility;
    private LocalDateTime updatedAt;

    @Builder
    public FeedStatusUpdateEvent(String feedUuid, Visibility visibility, LocalDateTime updatedAt) {
        this.feedUuid = feedUuid;
        this.visibility = visibility;
        this.updatedAt = updatedAt;
    }

}
