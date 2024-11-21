package com.mulmeong.feed.api.domain.event;

import com.mulmeong.feed.api.domain.model.Visibility;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateFeedStatusEvent {

    private String feedUuid;
    private Visibility visibility;

    @Builder
    public UpdateFeedStatusEvent(String feedUuid, Visibility visibility) {
        this.feedUuid = feedUuid;
        this.visibility = visibility;
    }

}
