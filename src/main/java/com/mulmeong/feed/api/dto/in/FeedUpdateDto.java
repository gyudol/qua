package com.mulmeong.feed.api.dto.in;

import com.mulmeong.feed.api.domain.entity.Feed;
import com.mulmeong.feed.api.domain.event.FeedUpdateEvent;
import com.mulmeong.feed.api.vo.in.FeedUpdateVo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedUpdateDto {

    private String feedUuid;
    private String title;
    private String content;
    private Long categoryId;
    private LocalDateTime updatedAt;

    public static FeedUpdateDto toDto(FeedUpdateVo requestVo, String feedUuid) {
        return FeedUpdateDto.builder()
            .feedUuid(feedUuid)
            .title(requestVo.getTitle())
            .content(requestVo.getContent())
            .categoryId(requestVo.getCategoryId())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public Feed toFeedEntity(Feed existingFeed) {   // don't update visibility
        return Feed.builder()
            .id(existingFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(existingFeed.getMemberUuid())
            .title(title)
            .content(content)
            .categoryId(categoryId)
            .visibility(existingFeed.getVisibility())
            .updatedAt(updatedAt)
            .build();
    }

    public FeedUpdateEvent toEventEntity() {    // to Kafka EventEntity
        return FeedUpdateEvent.builder()
            .feedUuid(feedUuid)
            .title(title)
            .content(content)
            .categoryId(categoryId)
            .updatedAt(updatedAt)
            .build();
    }

    @Builder
    public FeedUpdateDto(String feedUuid, String title, String content, Long categoryId,
        LocalDateTime updatedAt) {

        this.feedUuid = feedUuid;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.updatedAt = updatedAt;
    }

}
