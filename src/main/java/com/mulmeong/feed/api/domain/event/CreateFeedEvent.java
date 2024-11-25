package com.mulmeong.feed.api.domain.event;

import com.mulmeong.feed.api.domain.model.Hashtag;
import com.mulmeong.feed.api.domain.model.Media;
import com.mulmeong.feed.api.domain.model.Visibility;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateFeedEvent {

    private String feedUuid;
    private String memberUuid;
    private String title;
    private String content;
    private Long categoryId;
    private Visibility visibility;
    private List<Hashtag> hashtags;
    private List<Media> mediaList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public CreateFeedEvent(String feedUuid, String memberUuid, String title, String content,
        Long categoryId, Visibility visibility, List<Hashtag> hashtags, List<Media> mediaList,
        LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.visibility = visibility;
        this.hashtags = hashtags;
        this.mediaList = mediaList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
