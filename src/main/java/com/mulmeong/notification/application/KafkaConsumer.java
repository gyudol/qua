package com.mulmeong.notification.application;

import com.mulmeong.event.chat.ChattingCreateEvent;
import com.mulmeong.event.contents.*;
import com.mulmeong.event.contest.ContestVoteResultEvent;
import com.mulmeong.event.member.FollowCreateEvent;
import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberGradeUpdateEvent;
import com.mulmeong.event.report.ReportApproveEvent;
import com.mulmeong.notification.common.exception.BaseException;
import com.mulmeong.notification.common.response.BaseResponseStatus;
import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationStatus;
import com.mulmeong.notification.document.NotificationType;
import com.mulmeong.notification.infrastructure.NotificationStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final EventProcessService eventProcessService;

    @KafkaListener(topics = "${event.member.pub.topics.member-create.name}", containerFactory = "memberCreateListener")
    public void memberCreated(MemberCreateEvent message) {
        eventProcessService.saveMemberNotificationStatus(message);
    }

    @KafkaListener(topics = "${event.utility.pub.topics.feed-create-followers.name}",
            containerFactory = "feedCreateFollowersListener")
    public void getFollowersOfFeedCreator(FeedCreatedFollowersEvent message) {
        eventProcessService.saveFeedEvent(message);

    }

    @KafkaListener(topics = "${event.utility.pub.topics.shorts-create-followers.name}",
            containerFactory = "shortCreateFollowersListener")
    public void getFollowersOfShortsCreator(ShortsCreatedFollowersEvent message) {
        eventProcessService.saveShortsEvent(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-comment-create.name}",
            containerFactory = "feedCommentCreateListener")
    public void createFeedComment(FeedCommentCreateEvent message) {
        eventProcessService.saveFeedCommentEvent(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-recomment-create.name}",
            containerFactory = "feedRecommentCreateListener")
    public void createFeedRecomment(FeedRecommentCreateEvent message) {
        eventProcessService.saveFeedRecommentEvent(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-comment-create.name}",
            containerFactory = "shortsCommentCreateListener")
    public void createShortsComment(ShortsCommentCreateEvent message) {
        eventProcessService.saveShortsCommentEvent(message);
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-recomment-create.name}",
            containerFactory = "shortsRecommentCreateListener")
    public void createShortsRecomment(ShortsRecommentCreateEvent message) {
        eventProcessService.saveShortsRecommentEvent(message);
    }

    @KafkaListener(topics = "${event.utility.pub.topics.follow-create.name}",
            containerFactory = "followCreateListener")
    public void followCreated(FollowCreateEvent message) {
        eventProcessService.saveFollowEvent(message);
    }

    @KafkaListener(topics = "${event.utility.pub.topics.like-create.name}",
            containerFactory = "likeListener")
    public void likeCreated(LikeCreateEvent message) {
        eventProcessService.saveLikeEvent(message);
    }

    @KafkaListener(topics = "${event.contest.pub.topics.contest-result.name}",
            containerFactory = "contestWinnerListener")
    public void contestWinnerCreated(ContestVoteResultEvent message) {
        eventProcessService.saveContestResultEvent(message);
    }

    @KafkaListener(topics = "${event.grade.pub.topics.member-grade-update.name}",
            containerFactory = "gradeChangeListener")
    public void gradeUpdated(MemberGradeUpdateEvent message) {
        eventProcessService.saveMemberGradeEvent(message);
    }

    @KafkaListener(topics = "${event.chat.pub.topics.chatting-create.name}",
            containerFactory = "chattingCreateListener")
    public void chattingCreated(ChattingCreateEvent message) {
        eventProcessService.saveChattingEvent(message);
    }

    @KafkaListener(topics = "${event.report.pub.topics.report-approve.name}",
            containerFactory = "reportApproveListener")
    public void reportCreated(ReportApproveEvent message) {
        eventProcessService.saveReportEvent(message);
    }
}
