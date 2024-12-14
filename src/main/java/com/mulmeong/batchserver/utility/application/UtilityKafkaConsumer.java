package com.mulmeong.batchserver.utility.application;

import com.mulmeong.batchserver.comment.application.FeedCommentService;
import com.mulmeong.batchserver.comment.application.FeedRecommentService;
import com.mulmeong.batchserver.comment.application.ShortsCommentService;
import com.mulmeong.batchserver.comment.application.ShortsRecommentService;
import com.mulmeong.batchserver.common.exception.BaseException;
import com.mulmeong.batchserver.common.response.BaseResponseStatus;
import com.mulmeong.batchserver.feed.application.FeedService;
import com.mulmeong.event.utility.consume.DislikesCreateEvent;
import com.mulmeong.event.utility.consume.FeedCreateEvent;
import com.mulmeong.event.utility.consume.LikesCreateEvent;
import com.mulmeong.event.utility.consume.ShortsCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilityKafkaConsumer {

    private final FollowService followService;
    private final FeedService feedService;
    private final FeedCommentService feedCommentService;
    private final FeedRecommentService feedRecommentService;
    private final ShortsCommentService shortsCommentService;
    private final ShortsRecommentService shortsRecommentService;

    @KafkaListener(topics = "${event.feed.pub.topics.feed-create.name}",
            containerFactory = "feedCreateListener")
    public void feedCreated(FeedCreateEvent message) {
        log.info("feed create message: {}", message.getFeedUuid());
        followService.createFeedFollowerAlert(message);
    }

    @KafkaListener(topics = "${event.shorts.pub.topics.shorts-create.name}",
            containerFactory = "shortsCreateListener")
    public void shortsCreated(ShortsCreateEvent message) {
        log.info("feed create message: {}", message.getShortsUuid());
        followService.createShortsFollowerAlert(message);
    }

    @KafkaListener(topics = "${event.utility.pub.topics.like-create.name}",
            containerFactory = "likeCreateListener")
    public void likesCreated(LikesCreateEvent message) {
        String kind = message.getKind();
        log.info("like message: {}", kind);
        switch (kind) {
            case "feed":
                feedService.likeCountRenew(message);
                break;
            case "shorts":
                break;
            case "feed_comment":
                feedCommentService.likeCountRenew(message);
                break;
            case "feed_recomment":
                feedRecommentService.likeCountRenew(message);
                break;
            case "shorts_comment":
                shortsCommentService.likeCountRenew(message);
                break;
            case "shorts_recomment":
                shortsRecommentService.likeCountRenew(message);
                break;
            default:
                throw new BaseException(BaseResponseStatus.NOT_EXIST);
        }
    }

    @KafkaListener(topics = "${event.utility.pub.topics.dislike-create.name}",
            containerFactory = "dislikeCreateListener")
    public void dislikesCreated(DislikesCreateEvent message) {
        String kind = message.getKind();
        log.info("dislike message: {}", kind);
        switch (kind) {
            case "feed":
                feedService.dislikeCountRenew(message);
                break;
            case "shorts":
                break;
            case "feedComment":
                feedCommentService.dislikeCountRenew(message);
                break;
            case "feedRecomment":
                feedRecommentService.dislikeCountRenew(message);
                break;
            case "shortsComment":
                shortsCommentService.dislikeCountRenew(message);
                break;
            case "shortsRecomment":
                shortsRecommentService.dislikeCountRenew(message);
                break;
            default:
                throw new BaseException(BaseResponseStatus.NOT_EXIST);
        }
    }

}
