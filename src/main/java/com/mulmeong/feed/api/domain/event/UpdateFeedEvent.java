package com.mulmeong.feed.api.domain.event;

import com.mulmeong.feed.api.domain.model.Hashtag;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateFeedEvent {

    private String feedUuid;
    private String title;
    private String content;
    private Long categoryId;
    private List<Hashtag> hashtags;

    @Builder
    public UpdateFeedEvent(String feedUuid, String title, String content, Long categoryId,
        List<Hashtag> hashtags) {

        this.feedUuid = feedUuid;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.hashtags = hashtags;
    }

}
