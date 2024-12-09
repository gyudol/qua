package com.mulmeong.shorts.api.domain.event;

import com.mulmeong.shorts.api.domain.model.Hashtag;
import com.mulmeong.shorts.api.domain.model.Media;
import com.mulmeong.shorts.api.domain.model.Visibility;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsCreateEvent {

    private String shortsUuid;
    private String memberUuid;
    private String title;
    private Short playtime;
    private Visibility visibility;
    private List<Hashtag> hashtags;
    private Media media;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public ShortsCreateEvent(String shortsUuid, String memberUuid, String title, Short playtime,
        Visibility visibility, List<Hashtag> hashtags, Media media, LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.shortsUuid = shortsUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.playtime = playtime;
        this.visibility = visibility;
        this.hashtags = hashtags;
        this.media = media;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
