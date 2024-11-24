package com.mulmeong.feed.api.dto.in;

import com.mulmeong.feed.api.domain.entity.Feed;
import com.mulmeong.feed.api.domain.entity.FeedHashtag;
import com.mulmeong.feed.api.domain.entity.FeedMedia;
import com.mulmeong.feed.api.domain.event.CreateFeedEvent;
import com.mulmeong.feed.api.domain.model.Hashtag;
import com.mulmeong.feed.api.domain.model.Media;
import com.mulmeong.feed.api.domain.model.Visibility;
import com.mulmeong.feed.api.vo.in.CreateFeedRequestVo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateFeedRequestDto {

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

    public static CreateFeedRequestDto toDto(CreateFeedRequestVo requestVo) {
        LocalDateTime now = LocalDateTime.now();

        return CreateFeedRequestDto.builder()
            .feedUuid(UUID.randomUUID().toString())     // create feedUuid
            .memberUuid(requestVo.getMemberUuid())
            .title(requestVo.getTitle())
            .content(requestVo.getContent())
            .categoryId(requestVo.getCategoryId())
            .visibility(requestVo.getVisibility())
            .hashtags(requestVo.getHashtags())
            .mediaList(requestVo.getMediaList())
            .createdAt(now)
            .updatedAt(now)
            .build();
    }

    public Feed toFeedEntity() {
        return Feed.builder()
            .feedUuid(feedUuid)
            .memberUuid(memberUuid)
            .title(title)
            .content(content)
            .categoryId(categoryId)
            .visibility(visibility)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }

    public List<FeedMedia> toFeedMediaEntities() {
        if (mediaList == null) {
            mediaList = List.of();
        }

        return mediaList.stream()
            .map(media -> FeedMedia.builder()
                .mediaUuid(media.getMediaUuid())
                .feedUuid(feedUuid)
                .mediaType(media.getMediaType())
                .assets(media.getAssets())
                .build())
            .toList();
    }

    public FeedHashtag toFeedHashtagEntity() {
        if (hashtags == null) {
            hashtags = List.of();
        }

        return FeedHashtag.builder()
            .feedUuid(feedUuid)
            .hashtags(hashtags)
            .build();
    }

    public CreateFeedEvent toEventEntity() {    // to Kafka EventEntity
        return CreateFeedEvent.builder()
            .feedUuid(feedUuid)
            .memberUuid(memberUuid)
            .title(title)
            .content(content)
            .categoryId(categoryId)
            .visibility(visibility)
            .hashtags(hashtags)
            .mediaList(mediaList)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }

    @Builder
    public CreateFeedRequestDto(String feedUuid, String memberUuid, String title, String content,
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
