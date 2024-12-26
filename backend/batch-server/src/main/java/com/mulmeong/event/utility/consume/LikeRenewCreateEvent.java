package com.mulmeong.event.utility.consume;

import com.mulmeong.batchserver.comment.domain.document.FeedComment;
import com.mulmeong.batchserver.comment.domain.document.FeedRecomment;
import com.mulmeong.batchserver.comment.domain.document.ShortsComment;
import com.mulmeong.batchserver.comment.domain.document.ShortsRecomment;
import com.mulmeong.batchserver.feed.domain.document.FeedRead;
import com.mulmeong.batchserver.shorts.domain.document.ShortsRead;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class LikeRenewCreateEvent {

    private String kind;
    private String kindUuid;
    private Long likeCount;

    public FeedRead toFeedReadEntity(FeedRead feed, Long likeCount) {
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
                .likeCount(likeCount)
                .dislikeCount(feed.getDislikeCount())
                .netLikes(likeCount - feed.getDislikeCount())
                .commentCount(feed.getCommentCount())
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .build();
    }

    public ShortsRead toShortsReadEntity(ShortsRead shorts, Long likeCount) {
        return ShortsRead.builder()
                .id(shorts.getId())
                .shortsUuid(shorts.getShortsUuid())
                .memberUuid(shorts.getMemberUuid())
                .title(shorts.getTitle())
                .playtime(shorts.getPlaytime())
                .visibility(shorts.getVisibility())
                .media(shorts.getMedia())
                .likeCount(likeCount)
                .dislikeCount(shorts.getDislikeCount())
                .netLikes(likeCount - shorts.getDislikeCount())
                .commentCount(shorts.getCommentCount())
                .createdAt(shorts.getCreatedAt())
                .updatedAt(shorts.getUpdatedAt())
                .build();
    }

    public FeedComment toFeedCommentReadEntity(FeedComment feedComment, Long likeCount) {
        String cursor = String.format("%010d", likeCount)
                + String.format("%010d", 1000000000 - feedComment.getDislikeCount())
                + String.format("%010d", feedComment.getRecommentCount())
                + feedComment.getId();
        return FeedComment.builder()
                .id(feedComment.getId())
                .feedUuid(feedComment.getFeedUuid())
                .memberUuid(feedComment.getMemberUuid())
                .commentUuid(feedComment.getCommentUuid())
                .content(feedComment.getContent())
                .isDeleted(feedComment.isDeleted())
                .createdAt(feedComment.getCreatedAt())
                .updatedAt(feedComment.getUpdatedAt())
                .likeCount(likeCount)
                .dislikeCount(feedComment.getDislikeCount())
                .recommentCount(feedComment.getRecommentCount())
                .customCursor(cursor)
                .build();
    }

    public ShortsComment toShortsCommentReadEntity(ShortsComment shortsComment, Long likeCount) {
        String cursor = String.format("%010d", likeCount)
                + String.format("%010d", 1000000000 - shortsComment.getDislikeCount())
                + String.format("%010d", shortsComment.getRecommentCount())
                + shortsComment.getId();
        return ShortsComment.builder()
                .id(shortsComment.getId())
                .shortsUuid(shortsComment.getShortsUuid())
                .commentUuid(shortsComment.getCommentUuid())
                .content(shortsComment.getContent())
                .isDeleted(shortsComment.isDeleted())
                .createdAt(shortsComment.getCreatedAt())
                .updatedAt(shortsComment.getUpdatedAt())
                .likeCount(likeCount)
                .dislikeCount(shortsComment.getDislikeCount())
                .recommentCount(shortsComment.getRecommentCount())
                .customCursor(cursor)
                .build();
    }

    public FeedRecomment toFeedRecommentReadEntity(FeedRecomment feedRecomment, Long likeCount) {
        return FeedRecomment.builder()
                .id(feedRecomment.getId())
                .memberUuid(feedRecomment.getMemberUuid())
                .commentUuid(feedRecomment.getCommentUuid())
                .recommentUuid(feedRecomment.getRecommentUuid())
                .content(feedRecomment.getContent())
                .createdAt(feedRecomment.getCreatedAt())
                .updatedAt(feedRecomment.getUpdatedAt())
                .likeCount(likeCount)
                .dislikeCount(feedRecomment.getDislikeCount())
                .build();
    }

    public ShortsRecomment toShortsRecommentReadEntity(ShortsRecomment shortsRecomment, Long likeCount) {
        return ShortsRecomment.builder()
                .id(shortsRecomment.getId())
                .memberUuid(shortsRecomment.getMemberUuid())
                .commentUuid(shortsRecomment.getCommentUuid())
                .recommentUuid(shortsRecomment.getRecommentUuid())
                .content(shortsRecomment.getContent())
                .createdAt(shortsRecomment.getCreatedAt())
                .updatedAt(shortsRecomment.getUpdatedAt())
                .likeCount(likeCount)
                .dislikeCount(shortsRecomment.getDislikeCount())
                .build();
    }
}
