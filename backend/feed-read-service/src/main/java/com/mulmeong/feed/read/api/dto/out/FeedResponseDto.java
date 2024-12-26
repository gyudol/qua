package com.mulmeong.feed.read.api.dto.out;

import com.mulmeong.feed.read.api.domain.document.ElasticFeed;
import com.mulmeong.feed.read.api.domain.document.Feed;
import com.mulmeong.feed.read.api.domain.model.Hashtag;
import com.mulmeong.feed.read.api.domain.model.Media;
import com.mulmeong.feed.read.api.domain.model.Visibility;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedResponseDto {

    private String feedUuid;
    private String memberUuid;
    private String title;
    private String content;
    private String categoryName;
    private Visibility visibility;
    private List<Hashtag> hashtags;
    private List<Media> mediaList;
    private Long likeCount;
    private Long dislikeCount;
    private Long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FeedResponseDto fromDocument(Feed feed) {
        return FeedResponseDto.builder()
            .feedUuid(feed.getFeedUuid())
            .memberUuid(feed.getMemberUuid())
            .title(feed.getTitle())
            .content(feed.getContent())
            .categoryName(feed.getCategoryName())
            .visibility(feed.getVisibility())
            .hashtags(feed.getHashtags())
            .mediaList(feed.getMediaList())
            .likeCount(feed.getLikeCount())
            .dislikeCount(feed.getDislikeCount())
            .commentCount(feed.getCommentCount())
            .createdAt(feed.getCreatedAt())
            .updatedAt(feed.getUpdatedAt())
            .build();
    }

    public static FeedResponseDto fromDocument(ElasticFeed elasticFeed) {
        return FeedResponseDto.builder()
            .feedUuid(elasticFeed.getFeedUuid())
            .memberUuid(elasticFeed.getMemberUuid())
            .title(elasticFeed.getTitle())
            .content(elasticFeed.getContent())
            .categoryName(elasticFeed.getCategoryName())
            .visibility(elasticFeed.getVisibility())
            .hashtags(elasticFeed.getHashtags())
            .mediaList(elasticFeed.getMediaList())
            .likeCount(elasticFeed.getLikeCount())
            .dislikeCount(elasticFeed.getDislikeCount())
            .commentCount(elasticFeed.getCommentCount())
            .createdAt(elasticFeed.getCreatedAt().toLocalDateTime())
            .updatedAt(elasticFeed.getUpdatedAt().toLocalDateTime())
            .build();
    }

    @Builder
    public FeedResponseDto(String feedUuid, String memberUuid, String title, String content,
        String categoryName, Visibility visibility, List<Media> mediaList, List<Hashtag> hashtags,
        Long likeCount, Long commentCount, Long dislikeCount, LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
        this.visibility = visibility;
        this.mediaList = mediaList;
        this.hashtags = hashtags;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.dislikeCount = dislikeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
