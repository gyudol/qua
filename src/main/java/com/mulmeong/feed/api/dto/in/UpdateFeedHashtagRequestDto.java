package com.mulmeong.feed.api.dto.in;

import com.mulmeong.feed.api.domain.entity.Feed;
import com.mulmeong.feed.api.domain.entity.FeedHashtag;
import com.mulmeong.feed.api.domain.event.UpdateFeedHashtagEvent;
import com.mulmeong.feed.api.domain.model.Hashtag;
import com.mulmeong.feed.api.vo.in.UpdateFeedHashtagRequestVo;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateFeedHashtagRequestDto {

    private String feedUuid;
    private List<Hashtag> hashtags;
    private LocalDateTime updatedAt;

    public static UpdateFeedHashtagRequestDto toDto(UpdateFeedHashtagRequestVo requestVo,
        String feedUuid) {

        return UpdateFeedHashtagRequestDto.builder()
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

    public UpdateFeedHashtagEvent toEventEntity() {  // to Kafka EventEntity
        return UpdateFeedHashtagEvent.builder()
            .feedUuid(feedUuid)
            .hashtags(hashtags)
            .updatedAt(updatedAt)
            .build();
    }

    @Builder
    public UpdateFeedHashtagRequestDto(String feedUuid, List<Hashtag> hashtags,
        LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.hashtags = hashtags;
        this.updatedAt = updatedAt;
    }

}
