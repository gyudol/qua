package com.mulmeong.comment.read.application;

import com.mulmeong.event.comment.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final FeedCommentService feedCommentService;
    private final FeedRecommentService feedRecommentService;
    private final ShortsCommentService shortsCommentService;
    private final ShortsRecommentService shortsRecommentService;

    //feed comment
    @KafkaListener(topics = "${event.comment.pub.topics.feed-comment-create.name}",
                   containerFactory = "feedCommentCreateListener")
    public void createFeedComment(FeedCommentCreateEvent message) {
        feedCommentService.createFeedComment(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-comment-update.name}",
                   containerFactory = "feedCommentUpdateListener")
    public void updateFeedComment(FeedCommentUpdateEvent message) {
        feedCommentService.updateFeedComment(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-comment-delete.name}",
                   containerFactory = "feedCommentDeleteListener")
    public void deleteFeedComment(FeedCommentDeleteEvent message) {
        feedCommentService.deleteFeedComment(message);
    }

    //feed recomment
    @KafkaListener(topics = "${event.comment.pub.topics.feed-recomment-create.name}",
                   containerFactory = "feedRecommentCreateListener")
    public void createFeedRecomment(FeedRecommentCreateEvent message) {
        feedRecommentService.createFeedRecomment(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-recomment-update.name}",
                   containerFactory = "feedRecommentUpdateListener")
    public void updateFeedRecomment(FeedRecommentUpdateEvent message) {
        feedRecommentService.updateFeedRecomment(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-recomment-delete.name}",
                   containerFactory = "feedRecommentDeleteListener")
    public void deleteFeedRecomment(FeedRecommentDeleteEvent message) {
        feedRecommentService.deleteFeedRecomment(message);
    }

    //shorts comment
    @KafkaListener(topics = "${event.comment.pub.topics.shorts-comment-create.name}",
                   containerFactory = "shortsCommentCreateListener")
    public void createShortsComment(ShortsCommentCreateEvent message) {
        shortsCommentService.createShortsComment(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-comment-update.name}",
                   containerFactory = "shortsCommentUpdateListener")
    public void updateShortsComment(ShortsCommentUpdateEvent message) {
        shortsCommentService.updateShortsComment(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-comment-delete.name}",
                   containerFactory = "shortsCommentDeleteListener")
    public void deleteShortsComment(ShortsCommentDeleteEvent message) {
        shortsCommentService.deleteShortsComment(message);
    }

    //shorts recomment
    @KafkaListener(topics = "${event.comment.pub.topics.shorts-recomment-create.name}",
                   containerFactory = "shortsRecommentCreateListener")
    public void createShortsRecomment(ShortsRecommentCreateEvent message) {
        shortsRecommentService.createShortsRecomment(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-recomment-update.name}",
                   containerFactory = "shortsRecommentUpdateListener")
    public void updateShortsRecomment(ShortsRecommentUpdateEvent message) {
        shortsRecommentService.updateShortsRecomment(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-recomment-delete.name}",
                   containerFactory = "feedRecommentDeleteListener")
    public void deleteShortsRecomment(ShortsRecommentDeleteEvent message) {
        shortsRecommentService.deleteShortsRecomment(message);
    }

    /*
     * todo: like, dislike count, comment에 recomment 개수
     *       feed read db에 댓글 수
     */
}
