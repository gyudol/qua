package com.mulmeong.feed.read.api.domain.event;

import com.mulmeong.feed.read.api.domain.document.ElasticFeed;
import com.mulmeong.feed.read.api.domain.document.Feed;
import com.mulmeong.feed.read.api.domain.model.Hashtag;
import com.mulmeong.feed.read.api.domain.model.Visibility;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedStatusUpdateEvent {

    private String feedUuid;
    private Visibility visibility;
    private LocalDateTime updatedAt;

    public Feed toDocument(Feed existingFeed) {
        return Feed.builder()
            .id(existingFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(existingFeed.getMemberUuid())
            .title(existingFeed.getTitle())
            .content(existingFeed.getContent())
            .categoryName(existingFeed.getCategoryName())
            .visibility(visibility)
            .hashtags(existingFeed.getHashtags())
            .mediaList(existingFeed.getMediaList())
            .likeCount(existingFeed.getLikeCount())
            .dislikeCount(existingFeed.getDislikeCount())
            .netLikes(existingFeed.getNetLikes())
            .commentCount(existingFeed.getCommentCount())
            .createdAt(existingFeed.getCreatedAt())
            .updatedAt(updatedAt)
            .build();
    }

    public ElasticFeed toElasticFeedDocument(ElasticFeed existingElasticFeed) {
        return ElasticFeed.builder()
            .id(existingElasticFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(existingElasticFeed.getMemberUuid())
            .title(existingElasticFeed.getTitle())
            .content(existingElasticFeed.getContent())
            .categoryName(existingElasticFeed.getCategoryName())
            .visibility(visibility)
            .hashtags(existingElasticFeed.getHashtags())
            .mediaList(existingElasticFeed.getMediaList())
            .likeCount(existingElasticFeed.getLikeCount())
            .dislikeCount(existingElasticFeed.getDislikeCount())
            .netLikes(existingElasticFeed.getNetLikes())
            .commentCount(existingElasticFeed.getCommentCount())
            .createdAt(existingElasticFeed.getCreatedAt())
            .updatedAt(updatedAt.toString())
            .build();
    }

}
