package com.mulmeong.notification.application;

import com.mulmeong.event.chat.ChattingCreateEvent;
import com.mulmeong.event.contents.FeedCommentCreateEvent;
import com.mulmeong.event.contents.FeedRecommentCreateEvent;
import com.mulmeong.event.contents.ShortsCommentCreateEvent;
import com.mulmeong.event.contents.ShortsRecommentCreateEvent;
import com.mulmeong.event.contents.*;
import com.mulmeong.event.contest.ContestVoteResultEvent;
import com.mulmeong.event.member.FollowCreateEvent;
import com.mulmeong.event.member.MemberGradeUpdateEvent;
import com.mulmeong.event.report.ReportCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${event.feed.pub.topics.feed-create.name}",
            containerFactory = "feedCreateListener")
    public void feedCreated(FeedCreateEvent message) {
        log.info("message: {}", message.getContent());
    }

    @KafkaListener(topics = "${event.shorts.pub.topics.shorts-create.name}",
            containerFactory = "shortsCreateListener")
    public void shortsCreated(ShortsCreateEvent message) {
        log.info("message: {}", message.getTitle());
    }

    @KafkaListener(topics = "${event.utility.pub.topics.feed-create-followers.name}",
            containerFactory = "feedCreateFollowersListener")
    public void getFollowersOfFeedCreator(FeedCreatedFollowersEvent message) {
        log.info("message: {}", message.getFeedUuid());
    }

    @KafkaListener(topics = "${event.utility.pub.topics.shorts-create-followers.name}",
            containerFactory = "shortCreateFollowersListener")
    public void getFollowersOfShortsCreator(ShortsCreatedFollowersEvent message) {
        log.info("message: {}", message.getShortsUuid());
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-comment-create.name}",
            containerFactory = "feedCommentCreateListener")
    public void createFeedComment(FeedCommentCreateEvent message) {
        log.info("message: {}", message.getContent());
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-recomment-create.name}",
            containerFactory = "feedRecommentCreateListener")
    public void createFeedRecomment(FeedRecommentCreateEvent message) {
        log.info("message: {}", message.getContent());
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-comment-create.name}",
            containerFactory = "shortsCommentCreateListener")
    public void createShortsComment(ShortsCommentCreateEvent message) {
        log.info("message: {}", message.getContent());
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-recomment-create.name}",
            containerFactory = "shortsRecommentCreateListener")
    public void createShortsRecomment(ShortsRecommentCreateEvent message) {
        log.info("message: {}", message.getContent());
    }

    @KafkaListener(topics = "${event.utility.pub.topics.follow-create.name}",
            containerFactory = "followCreateListener")
    public void followCreated(FollowCreateEvent message) {
    }

    @KafkaListener(topics = "${event.utility.pub.topics.like-create.name}",
            containerFactory = "likeListener")
    public void likeCreated(LikeCreateEvent message) {
    }

    @KafkaListener(topics = "${event.contest.pub.topics.contest-result.name}",
            containerFactory = "contestWinnerListener")
    public void contestWinnerCreated(ContestVoteResultEvent message) {
    }

    @KafkaListener(topics = "${event.member-grade.pub.topics.member-grade-update.name}",
            containerFactory = "gradeChangeListener")
    public void gradeUpdated(MemberGradeUpdateEvent message) {
    }

    @KafkaListener(topics = "${event.chat.pub.topics.chatting-create.name}",
            containerFactory = "chattingCreateListener")
    public void chattingCreated(ChattingCreateEvent message) {
        log.info("message: {}", message.getMessage());
    }

    @KafkaListener(topics = "${event.report.pub.topics.report-create.name}",
            containerFactory = "reportCreateListener")
    public void reportCreated(ReportCreateEvent message) {
    }

}
