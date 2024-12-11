package com.mulmeong.feed.read.api.domain.event;

import com.mulmeong.feed.read.api.domain.document.Feed;
import com.mulmeong.feed.read.api.domain.entity.FeedHashtag;
import com.mulmeong.feed.read.api.domain.model.Hashtag;
import com.mulmeong.feed.read.api.domain.model.Media;
import com.mulmeong.feed.read.api.domain.model.Visibility;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedCreateEvent {

    private String feedUuid;
    private String memberUuid;
    private String title;
    private String content;
    private String categoryName;
    private Visibility visibility;
    private List<Hashtag> hashtags;
    private List<Media> mediaList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Feed toFeedDocument() {
        return Feed.builder()
            .feedUuid(feedUuid)
            .memberUuid(memberUuid)
            .title(title)
            .content(content)
            .categoryName(categoryName)
            .visibility(visibility)
            .hashtags(hashtags)
            .mediaList(mediaList)
            .likeCount(0L)
            .dislikeCount(0L)
            .netLikes(0L)
            .commentCount(0L)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }

    public List<FeedHashtag> toHashtagEntities() {
        return hashtags.stream()
            .map(hashtag -> FeedHashtag.builder()
                .name(hashtag.getName())
                .build())
            .toList();
    }

}
