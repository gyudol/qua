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
public class DislikesCreateEvent {

    private String kind;
    private String kindUuid;
    private Long dislikeCount;


    public FeedRead toFeedReadEntity(FeedRead feed, Long dislikeCount) {
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
                .dislikeCount(dislikeCount)
                .netLikes(feed.getLikeCount() - dislikeCount)
                .commentCount(feed.getCommentCount())
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .build();
    }

    public ShortsRead toShortsReadEntity(ShortsRead shorts, Long dislikeCount) {
        return ShortsRead.builder()
                .id(shorts.getId())
                .shortsUuid(shorts.getShortsUuid())
                .memberUuid(shorts.getMemberUuid())
                .title(shorts.getTitle())
                .playtime(shorts.getPlaytime())
                .visibility(shorts.getVisibility())
                .hashtags(shorts.getHashtags())
                .media(shorts.getMedia())
                .likeCount(shorts.getLikeCount())
                .dislikeCount(dislikeCount)
                .netLikes(shorts.getLikeCount() - dislikeCount)
                .commentCount(shorts.getCommentCount())
                .createdAt(shorts.getCreatedAt())
                .updatedAt(shorts.getUpdatedAt())
                .build();
    }

    public FeedComment toFeedCommentReadEntity(FeedComment feedComment, Long dislikeCount) {
        String cursor = String.format("%010d", feedComment.getLikeCount())
                + String.format("%010d", 1000000000 - dislikeCount)
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
                .likeCount(feedComment.getLikeCount())
                .dislikeCount(dislikeCount)
                .recommentCount(feedComment.getRecommentCount())
                .customCursor(cursor)
                .build();
    }


    public ShortsComment toShortsCommentReadEntity(ShortsComment shortsComment, Long dislikeCount) {
        String cursor = String.format("%010d", shortsComment.getLikeCount())
                + String.format("%010d", 1000000000 - dislikeCount)
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
                .likeCount(shortsComment.getLikeCount())
                .dislikeCount(dislikeCount)
                .recommentCount(shortsComment.getRecommentCount())
                .customCursor(cursor)
                .build();
    }

    public FeedRecomment toFeedRecommentReadEntity(FeedRecomment feedRecomment, Long dislikeCount) {
        return FeedRecomment.builder()
                .id(feedRecomment.getId())
                .memberUuid(feedRecomment.getMemberUuid())
                .commentUuid(feedRecomment.getCommentUuid())
                .recommentUuid(feedRecomment.getRecommentUuid())
                .content(feedRecomment.getContent())
                .createdAt(feedRecomment.getCreatedAt())
                .updatedAt(feedRecomment.getUpdatedAt())
                .likeCount(feedRecomment.getLikeCount())
                .dislikeCount(dislikeCount)
                .build();
    }

    public ShortsRecomment toShortsRecommentReadEntity(ShortsRecomment shortsRecomment, Long dislikeCount) {
        return ShortsRecomment.builder()
                .id(shortsRecomment.getId())
                .memberUuid(shortsRecomment.getMemberUuid())
                .commentUuid(shortsRecomment.getCommentUuid())
                .recommentUuid(shortsRecomment.getRecommentUuid())
                .content(shortsRecomment.getContent())
                .createdAt(shortsRecomment.getCreatedAt())
                .updatedAt(shortsRecomment.getUpdatedAt())
                .likeCount(shortsRecomment.getLikeCount())
                .dislikeCount(dislikeCount)
                .build();
    }
}
