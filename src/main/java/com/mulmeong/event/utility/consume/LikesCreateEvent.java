package com.mulmeong.event.utility.consume;

import com.mulmeong.batchserver.feed.domain.document.FeedRead;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class LikesCreateEvent {

    private String kind;
    private String kindUuid;
    private Long likeCount;

    public FeedRead toEntity(FeedRead feed, Long count) {
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
                .likeCount(count)
                .dislikeCount(feed.getDislikeCount())
                .netLikes(count - feed.getDislikeCount())
                .commentCount(feed.getCommentCount())
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .build();
    }

}
