package com.mulmeong.batchserver.utility.application;

import com.mulmeong.batchserver.comment.application.FeedCommentService;
import com.mulmeong.batchserver.comment.application.FeedRecommentService;
import com.mulmeong.batchserver.comment.application.ShortsCommentService;
import com.mulmeong.batchserver.comment.application.ShortsRecommentService;
import com.mulmeong.batchserver.common.exception.BaseException;
import com.mulmeong.batchserver.common.response.BaseResponseStatus;
import com.mulmeong.batchserver.feed.application.FeedService;
import com.mulmeong.batchserver.shorts.application.ShortsService;
import com.mulmeong.event.utility.consume.*;
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
    private final ShortsService shortsService;
    private final FeedCommentService feedCommentService;
    private final FeedRecommentService feedRecommentService;
    private final ShortsCommentService shortsCommentService;
    private final ShortsRecommentService shortsRecommentService;

    @KafkaListener(topics = "${event.utility.pub.topics.follow-create.name}",
            containerFactory = "followCreateListener")
    public void followCreated(FollowCreateEvent message) {
        log.info("follow create message: {}", message.getTargetUuid());
        followService.createFollowerRenew(message);
    }

    @KafkaListener(topics = "${event.utility.pub.topics.unfollow.name}",
            containerFactory = "unfollowListener")
    public void unfollowed(UnfollowEvent message) {
        log.info("unfollow message: {}", message.getTargetUuid());
        followService.createFollowerRenew(message);
    }

    @KafkaListener(topics = "${event.feed.pub.topics.feed-create.name}",
            containerFactory = "feedCreateListener")
    public void feedCreated(FeedCreateEvent message) {
        log.info("feed create message: {}", message.getFeedUuid());
        followService.createFeedFollowerAlert(message);
    }

    @KafkaListener(topics = "${event.shorts.pub.topics.shorts-create.name}",
            containerFactory = "shortsCreateListener")
    public void shortsCreated(ShortsCreateEvent message) {
        log.info("shorts create message: {}", message.getShortsUuid());
        followService.createShortsFollowerAlert(message);
    }

    @KafkaListener(topics = "${event.utility.pub.topics.like-renew-create.name}",
            containerFactory = "likeRenewCreateListener")
    public void likesRenewCreated(LikeRenewCreateEvent message) {
        String kind = message.getKind();
        log.info("like message: {}", kind);
        switch (kind) {
            case "feed":
                feedService.likeCountRenew(message);
                break;
            case "shorts":
                break;
            case "feed-comment":
                feedCommentService.likeCountRenew(message);
                break;
            case "feed-recomment":
                feedRecommentService.likeCountRenew(message);
                break;
            case "shorts-comment":
                shortsCommentService.likeCountRenew(message);
                break;
            case "shorts-recomment":
                shortsRecommentService.likeCountRenew(message);
                break;
            default:
                throw new BaseException(BaseResponseStatus.NOT_EXIST);
        }
    }

    @KafkaListener(topics = "${event.utility.pub.topics.dislike-renew-create.name}",
            containerFactory = "dislikeRenewCreateListener")
    public void dislikesCreated(DislikeRenewCreateEvent message) {
        String kind = message.getKind();
        log.info("dislike message: {}", kind);
        switch (kind) {
            case "feed":
                feedService.dislikeCountRenew(message);
                break;
            case "shorts":
                break;
            case "feed-comment":
                feedCommentService.dislikeCountRenew(message);
                break;
            case "feed-recomment":
                feedRecommentService.dislikeCountRenew(message);
                break;
            case "shorts-comment":
                shortsCommentService.dislikeCountRenew(message);
                break;
            case "shorts-recomment":
                shortsRecommentService.dislikeCountRenew(message);
                break;
            default:
                throw new BaseException(BaseResponseStatus.NOT_EXIST);
        }
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-comment-create.name}",
            containerFactory = "feedCommentCreateListener")
    public void feedCommentCreated(FeedCommentCreateEvent message) {
        log.info("feed comment create message: {}", message.getFeedUuid());
        feedService.feedCommentCountRenew(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-comment-create.name}",
            containerFactory = "shortsCommentCreateListener")
    public void shortsCommentCreated(ShortsCommentCreateEvent message) {
        log.info("shorts comment create message: {}", message.getShortsUuid());
        shortsService.shortsCommentCountRenew(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-comment-delete.name}",
            containerFactory = "feedCommentDeleteListener")
    public void shortsCommentCreated(FeedCommentDeleteEvent message) {
        feedService.feedCommentCountRenew(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-comment-delete.name}",
            containerFactory = "shortsCommentDeleteListener")
    public void shortsCommentDeleted(ShortsCommentDeleteEvent message) {
        shortsService.shortsCommentCountRenew(message);
    }

}
