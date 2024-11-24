package com.mulmeong.feed.api.domain.event;

import com.mulmeong.feed.api.domain.model.Hashtag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateFeedHashtagEvent {

    private String feedUuid;
    private List<Hashtag> hashtags;
    private LocalDateTime updatedAt;

    @Builder
    public UpdateFeedHashtagEvent(String feedUuid, List<Hashtag> hashtags,
        LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.hashtags = hashtags;
        this.updatedAt = updatedAt;
    }

}
