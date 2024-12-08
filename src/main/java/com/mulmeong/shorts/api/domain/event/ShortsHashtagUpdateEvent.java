package com.mulmeong.shorts.api.domain.event;

import com.mulmeong.shorts.api.domain.model.Hashtag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public class ShortsHashtagUpdateEvent {

    private String shortsUuid;
    private List<Hashtag> hashtags;
    private LocalDateTime updatedAt;

    @Builder
    public ShortsHashtagUpdateEvent(String shortsUuid, List<Hashtag> hashtags,
        LocalDateTime updatedAt) {

        this.shortsUuid = shortsUuid;
        this.hashtags = hashtags;
        this.updatedAt = updatedAt;
    }

}
