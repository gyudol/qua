package com.mulmeong.shorts.api.domain.event;

import com.mulmeong.shorts.api.domain.model.Visibility;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsStatusUpdateEvent {

    private String shortsUuid;
    private Visibility visibility;
    private LocalDateTime updatedAt;

    @Builder
    public ShortsStatusUpdateEvent(String shortsUuid, Visibility visibility,
        LocalDateTime updatedAt) {

        this.shortsUuid = shortsUuid;
        this.visibility = visibility;
        this.updatedAt = updatedAt;
    }

}
