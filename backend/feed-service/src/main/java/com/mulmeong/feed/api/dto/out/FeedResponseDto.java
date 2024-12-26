package com.mulmeong.feed.api.dto.out;

import com.mulmeong.feed.api.domain.entity.Feed;
import com.mulmeong.feed.api.domain.document.FeedHashtag;
import com.mulmeong.feed.api.domain.document.FeedMedia;
import com.mulmeong.feed.api.domain.model.Hashtag;
import com.mulmeong.feed.api.domain.model.Media;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedResponseDto {

    private String feedUuid;
    private String title;
    private String content;
    private List<Hashtag> hashtags;
    private List<Media> mediaList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FeedResponseDto fromEntity(Feed feed, FeedHashtag feedHashtag,
        List<FeedMedia> mediaList) {

        return FeedResponseDto.builder()
            .feedUuid(feed.getFeedUuid())
            .title(feed.getTitle())
            .content(feed.getContent())
            .hashtags(feedHashtag.getHashtags())
            .mediaList(mediaList.stream()
                .map(feedMedia -> Media.builder()
                    .mediaUuid(feedMedia.getMediaUuid())
                    .mediaType(feedMedia.getMediaType())
                    .assets(feedMedia.getAssets()).build()).toList())
            .createdAt(feed.getCreatedAt())
            .updatedAt(feed.getUpdatedAt())
            .build();
    }

    @Builder
    public FeedResponseDto(String feedUuid, String title, String content, List<Hashtag> hashtags,
        List<Media> mediaList, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.title = title;
        this.content = content;
        this.hashtags = hashtags;
        this.mediaList = mediaList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
