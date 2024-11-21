package com.mulmeong.feed.api.dto.in;

import com.mulmeong.feed.api.domain.Feed;
import com.mulmeong.feed.api.domain.FeedHashtag;
import com.mulmeong.feed.api.domain.FeedMedia;
import com.mulmeong.feed.api.domain.event.CreateFeedEvent;
import com.mulmeong.feed.api.domain.model.Hashtag;
import com.mulmeong.feed.api.domain.model.Media;
import com.mulmeong.feed.api.domain.model.Visibility;
import com.mulmeong.feed.api.vo.in.CreateFeedRequestVo;
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

    public static CreateFeedRequestDto toDto(CreateFeedRequestVo requestVo) {
        return CreateFeedRequestDto.builder()
            .memberUuid(requestVo.getMemberUuid())
            .title(requestVo.getTitle())
            .content(requestVo.getContent())
            .categoryId(requestVo.getCategoryId())
            .hashtags(requestVo.getHashtags())
            .mediaList(requestVo.getMediaList())
            .build();
    }

    public Feed toFeedEntity() {    // Default visibility: VISIBLE
        return Feed.builder()
            .feedUuid(feedUuid = UUID.randomUUID().toString())
            .memberUuid(memberUuid)
            .title(title)
            .content(content)
            .categoryId(categoryId)
            .visibility(visibility = Visibility.VISIBLE)
            .build();
    }

    public List<FeedMedia> toFeedMediaEntities() {
        if (mediaList == null) {
            return List.of();
        }

        return mediaList.stream()
            .map(media -> FeedMedia.builder()
                .feedUuid(feedUuid)
                .mediaUrl(media.getMediaUrl())
                .mediaType(media.getMediaType())
                .description(media.getDescription())
                .build())
            .toList();
    }

    public List<FeedHashtag> toFeedHashtagEntities() {
        if (hashtags == null) {
            return List.of();
        }

        return hashtags.stream()
            .map(hashtag -> FeedHashtag.builder()
                .feedUuid(feedUuid)
                .name(hashtag.getName())
                .build())
            .toList();
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
            .build();
    }

    @Builder
    public CreateFeedRequestDto(String memberUuid, String title, String content, Long categoryId,
        List<Hashtag> hashtags, List<Media> mediaList) {
        this.memberUuid = memberUuid;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.hashtags = hashtags;
        this.mediaList = mediaList;
    }

}
