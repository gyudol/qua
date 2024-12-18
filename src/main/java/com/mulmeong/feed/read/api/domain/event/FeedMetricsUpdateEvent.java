package com.mulmeong.feed.read.api.domain.event;

import com.mulmeong.feed.read.api.domain.document.ElasticFeed;
import com.mulmeong.feed.read.api.domain.document.Feed;
import java.time.ZoneId;
import lombok.Getter;

@Getter
public class FeedMetricsUpdateEvent {

    private String feedUuid;
    private Long likeCount;
    private Long dislikeCount;
    private Long commentCount;

    public Feed toDocument(Feed existingFeed) {
        return Feed.builder()
            .id(existingFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(existingFeed.getMemberUuid())
            .title(existingFeed.getTitle())
            .content(existingFeed.getContent())
            .categoryName(existingFeed.getCategoryName())
            .visibility(existingFeed.getVisibility())
            .hashtags(existingFeed.getHashtags())
            .mediaList(existingFeed.getMediaList())
            .likeCount(likeCount)
            .dislikeCount(dislikeCount)
            .netLikes(likeCount - dislikeCount)
            .commentCount(commentCount)
            .createdAt(existingFeed.getCreatedAt())
            .updatedAt(existingFeed.getUpdatedAt())
            .build();
    }

    public ElasticFeed toElasticDocument(ElasticFeed existingElasticFeed) {
        return ElasticFeed.builder()
            .id(existingElasticFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(existingElasticFeed.getMemberUuid())
            .title(existingElasticFeed.getTitle())
            .content(existingElasticFeed.getContent())
            .categoryName(existingElasticFeed.getCategoryName())
            .visibility(existingElasticFeed.getVisibility())
            .hashtags(existingElasticFeed.getHashtags())
            .mediaList(existingElasticFeed.getMediaList())
            .likeCount(likeCount)
            .dislikeCount(dislikeCount)
            .netLikes(likeCount - dislikeCount)
            .commentCount(commentCount)
            .createdAt(existingElasticFeed.getCreatedAt())
            .updatedAt(existingElasticFeed.getUpdatedAt())
            .build();
    }

}
