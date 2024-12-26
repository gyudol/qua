package com.mulmeong.feed.read.api.domain.event;

import com.mulmeong.feed.read.api.domain.document.ElasticFeed;
import com.mulmeong.feed.read.api.domain.document.Feed;
import com.mulmeong.feed.read.api.domain.model.Hashtag;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedHashtagUpdateEvent {

    private String feedUuid;
    private List<Hashtag> hashtags;
    private LocalDateTime updatedAt;

    public Feed toFeedDocument(Feed existingFeed) {
        return Feed.builder()
            .id(existingFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(existingFeed.getMemberUuid())
            .title(existingFeed.getTitle())
            .content(existingFeed.getContent())
            .categoryName(existingFeed.getCategoryName())
            .visibility(existingFeed.getVisibility())
            .hashtags(hashtags)
            .mediaList(existingFeed.getMediaList())
            .likeCount(existingFeed.getLikeCount())
            .dislikeCount(existingFeed.getDislikeCount())
            .netLikes(existingFeed.getNetLikes())
            .commentCount(existingFeed.getCommentCount())
            .createdAt(existingFeed.getCreatedAt())
            .updatedAt(updatedAt)
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
            .hashtags(hashtags)
            .mediaList(existingElasticFeed.getMediaList())
            .likeCount(existingElasticFeed.getLikeCount())
            .dislikeCount(existingElasticFeed.getDislikeCount())
            .netLikes(existingElasticFeed.getNetLikes())
            .commentCount(existingElasticFeed.getCommentCount())
            .createdAt(existingElasticFeed.getCreatedAt())
            .updatedAt(updatedAt.atZone(ZoneId.of("Asia/Seoul")))
            .build();
    }

}
