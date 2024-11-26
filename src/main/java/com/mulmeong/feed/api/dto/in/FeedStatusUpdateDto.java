package com.mulmeong.feed.api.dto.in;

import com.mulmeong.feed.api.domain.entity.Feed;
import com.mulmeong.feed.api.domain.event.FeedStatusUpdateEvent;
import com.mulmeong.feed.api.domain.model.Visibility;
import com.mulmeong.feed.api.vo.in.FeedStatusUpdateVo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedStatusUpdateDto {

    private String feedUuid;
    private Visibility visibility;
    private LocalDateTime updatedAt;

    public static FeedStatusUpdateDto toDto(FeedStatusUpdateVo requestVo,
        String feedUuid) {

        return FeedStatusUpdateDto.builder()
            .feedUuid(feedUuid)
            .visibility(requestVo.getVisibility())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public Feed toFeedEntity(Feed existingFeed) {   // update visibility
        return Feed.builder()
            .id(existingFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(existingFeed.getMemberUuid())
            .title(existingFeed.getTitle())
            .content(existingFeed.getContent())
            .categoryId(existingFeed.getCategoryId())
            .visibility(visibility)
            .updatedAt(updatedAt)
            .build();
    }

    public FeedStatusUpdateEvent toEventEntity() {  // to Kafka EventEntity
        return FeedStatusUpdateEvent.builder()
            .feedUuid(feedUuid)
            .visibility(visibility)
            .updatedAt(updatedAt)
            .build();
    }

    @Builder
    public FeedStatusUpdateDto(String feedUuid, Visibility visibility,
        LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.visibility = visibility;
        this.updatedAt = updatedAt;
    }

}
