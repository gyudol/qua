package com.mulmeong.feed.api.dto.in;

import com.mulmeong.feed.api.domain.entity.Feed;
import com.mulmeong.feed.api.domain.entity.FeedHashtag;
import com.mulmeong.feed.api.domain.event.FeedHashtagUpdateEvent;
import com.mulmeong.feed.api.domain.model.Hashtag;
import com.mulmeong.feed.api.vo.in.FeedHashtagUpdateVo;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedHashtagUpdateDto {

    private String feedUuid;
    private List<Hashtag> hashtags;
    private LocalDateTime updatedAt;

    public static FeedHashtagUpdateDto toDto(FeedHashtagUpdateVo requestVo,
        String feedUuid) {

        return FeedHashtagUpdateDto.builder()
            .feedUuid(feedUuid)
            .hashtags(requestVo.getHashtags())
            .updatedAt(LocalDateTime.now())
            .build();
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

    public Feed toFeedEntity(Feed existingFeed) {
        return Feed.builder()
            .id(existingFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(existingFeed.getMemberUuid())
            .title(existingFeed.getTitle())
            .content(existingFeed.getContent())
            .categoryId(existingFeed.getCategoryId())
            .visibility(existingFeed.getVisibility())
            .updatedAt(updatedAt)
            .build();
    }

    public FeedHashtagUpdateEvent toEventEntity() {  // to Kafka EventEntity
        return FeedHashtagUpdateEvent.builder()
            .feedUuid(feedUuid)
            .hashtags(hashtags)
            .updatedAt(updatedAt)
            .build();
    }

    @Builder
    public FeedHashtagUpdateDto(String feedUuid, List<Hashtag> hashtags,
        LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.hashtags = hashtags;
        this.updatedAt = updatedAt;
    }

}
