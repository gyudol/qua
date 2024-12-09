package com.mulmeong.shorts.read.api.domain.event;

import com.mulmeong.shorts.read.api.domain.document.Shorts;
import com.mulmeong.shorts.read.api.domain.model.Hashtag;
import com.mulmeong.shorts.read.api.domain.model.Media;
import com.mulmeong.shorts.read.api.domain.model.Visibility;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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

    public Shorts toDocument() {
        return Shorts.builder()
            .shortsUuid(shortsUuid)
            .memberUuid(memberUuid)
            .title(title)
            .playtime(playtime)
            .visibility(visibility)
            .hashtags(hashtags)
            .media(media)
            .likeCount(0L)
            .dislikeCount(0L)
            .netLikes(0L)
            .commentCount(0L)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }

}
