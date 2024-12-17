package com.mulmeong.event.utility.consume;

import com.mulmeong.batchserver.feed.domain.document.FeedRead;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class FeedCommentCreateEvent {

    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private boolean isDeleted;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedRead toFeedReadEntity(FeedRead feed, Long commentCount) {
        return FeedRead.builder()
                .id(feed.getId())
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
                .netLikes(feed.getNetLikes())
                .commentCount(commentCount)
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .build();
    }
}
