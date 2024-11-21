package com.mulmeong.comment.read.application;

import com.mulmeong.event.*;
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
    @KafkaListener(topics = "feed-comment-create",
                   groupId = "comment-read",
                   containerFactory = "feedCommentCreateListener")
    public void createFeedComment(FeedCommentCreateEvent message) {
        feedCommentService.createFeedComment(message);
    }

    @KafkaListener(topics = "feed-comment-update",
                   groupId = "comment-read",
                   containerFactory = "feedCommentUpdateListener")
    public void updateFeedComment(FeedCommentUpdateEvent message) {
        feedCommentService.updateFeedComment(message);
    }

    @KafkaListener(topics = "feed-comment-delete",
                   groupId = "comment-read",
                   containerFactory = "feedCommentDeleteListener")
    public void deleteFeedComment(FeedCommentDeleteEvent message) {
        feedCommentService.deleteFeedComment(message);
    }

    //feed recomment
    @KafkaListener(topics = "feed-recomment-create",
                   groupId = "comment-read",
                   containerFactory = "feedRecommentCreateListener")
    public void createFeedRecomment(FeedRecommentCreateEvent message) {
        feedRecommentService.createFeedRecomment(message);
    }

    @KafkaListener(topics = "feed-recomment-update",
                   groupId = "comment-read",
                   containerFactory = "feedRecommentUpdateListener")
    public void updateFeedRecomment(FeedRecommentUpdateEvent message) {
        feedRecommentService.updateFeedRecomment(message);
    }

    @KafkaListener(topics = "feed-recomment-delete",
                   groupId = "comment-read",
                   containerFactory = "feedRecommentDeleteListener")
    public void deleteFeedRecomment(FeedRecommentDeleteEvent message) {
        feedRecommentService.deleteFeedRecomment(message);
    }

    //shorts comment
    @KafkaListener(topics = "shorts-comment-create",
                   groupId = "comment-read",
                   containerFactory = "shortsCommentCreateListener")
    public void createShortsComment(ShortsCommentCreateEvent message) {
        shortsCommentService.createShortsComment(message);
    }

    @KafkaListener(topics = "shorts-comment-update",
                   groupId = "comment-read",
                   containerFactory = "shortsCommentUpdateListener")
    public void updateShortsComment(ShortsCommentUpdateEvent message) {
        shortsCommentService.updateShortsComment(message);
    }

    @KafkaListener(topics = "shorts-comment-delete",
                   groupId = "comment-read",
                   containerFactory = "shortsCommentDeleteListener")
    public void deleteShortsComment(ShortsCommentDeleteEvent message) {
        shortsCommentService.deleteShortsComment(message);
    }

    //shorts recomment
    @KafkaListener(topics = "shorts-recomment-create",
                   groupId = "comment-read",
                   containerFactory = "shortsRecommentCreateListener")
    public void createShortsRecomment(ShortsRecommentCreateEvent message) {
        shortsRecommentService.createShortsRecomment(message);
    }

    @KafkaListener(topics = "shorts-recomment-update",
                   groupId = "comment-read",
                   containerFactory = "shortsRecommentUpdateListener")
    public void updateShortsRecomment(ShortsRecommentUpdateEvent message) {
        shortsRecommentService.updateShortsRecomment(message);
    }

    @KafkaListener(topics = "shorts-recomment-delete",
                   groupId = "comment-read",
                   containerFactory = "feedRecommentDeleteListener")
    public void deleteShortsRecomment(ShortsRecommentDeleteEvent message) {
        shortsRecommentService.deleteShortsRecomment(message);
    }

    /*
     * todo: like, dislike count, comment에 recomment 개수
     *       feed read db에 댓글 수
     */
}
