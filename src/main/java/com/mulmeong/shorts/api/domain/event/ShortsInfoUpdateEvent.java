package com.mulmeong.shorts.api.domain.event;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsInfoUpdateEvent {

    private String shortsUuid;
    private String title;
    private Short playtime;
    private LocalDateTime updatedAt;

    @Builder
    public ShortsInfoUpdateEvent(String shortsUuid, String title, Short playtime,
        LocalDateTime updatedAt) {

        this.shortsUuid = shortsUuid;
        this.title = title;
        this.playtime = playtime;
        this.updatedAt = updatedAt;
    }
}
