package com.mulmeong.event.utility.produce;

import com.mulmeong.batchserver.feed.domain.document.FeedRead;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedMetricsUpdateEvent {

    private String feedUuid;
    private Long likeCount;
    private Long dislikeCount;
    private Long commentCount;

    public static FeedMetricsUpdateEvent toDto(FeedRead feedRead) {
        return FeedMetricsUpdateEvent.builder()
                .feedUuid(feedRead.getFeedUuid())
                .likeCount(feedRead.getLikeCount())
                .dislikeCount(feedRead.getDislikeCount())
                .commentCount(feedRead.getCommentCount())
                .build();
    }
}
