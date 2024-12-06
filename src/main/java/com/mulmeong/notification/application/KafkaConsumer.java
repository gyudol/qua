package com.mulmeong.notification.application;

import com.mulmeong.event.chat.ChattingCreateEvent;
import com.mulmeong.event.contents.*;
import com.mulmeong.event.contest.ContestVoteResultEvent;
import com.mulmeong.event.member.FollowCreateEvent;
import com.mulmeong.event.member.MemberGradeUpdateEvent;
import com.mulmeong.event.report.ReportApproveEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final EventProcessService eventProcessService;
    private final SseService sseService;

    @KafkaListener(topics = "${event.utility.pub.topics.feed-create-followers.name}",
            containerFactory = "feedCreateFollowersListener")
    public void getFollowersOfFeedCreator(FeedCreatedFollowersEvent message) {
        String event = createEventName("FEED_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveFeedEvent(message));
    }

    @KafkaListener(topics = "${event.utility.pub.topics.shorts-create-followers.name}",
            containerFactory = "shortCreateFollowersListener")
    public void getFollowersOfShortsCreator(ShortsCreatedFollowersEvent message) {
        String event = createEventName("SHORTS_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveShortsEvent(message));
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-comment-create.name}",
            containerFactory = "feedCommentCreateListener")
    public void createFeedComment(FeedCommentCreateEvent message) {
        sseService.send(eventProcessService.saveFeedCommentEvent(message));
    }

    @KafkaListener(topics = "${event.comment.pub.topics.feed-recomment-create.name}",
            containerFactory = "feedRecommentCreateListener")
    public void createFeedRecomment(FeedRecommentCreateEvent message) {
        String event = createEventName("FEED_RECOMMENT_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveFeedRecommentEvent(message));
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-comment-create.name}",
            containerFactory = "shortsCommentCreateListener")
    public void createShortsComment(ShortsCommentCreateEvent message) {
        String event = createEventName("SHORTS_COMMENT_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveShortsCommentEvent(message));
    }

    @KafkaListener(topics = "${event.comment.pub.topics.shorts-recomment-create.name}",
            containerFactory = "shortsRecommentCreateListener")
    public void createShortsRecomment(ShortsRecommentCreateEvent message) {
        String event = createEventName("SHORTS_RECOMMENT_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveShortsRecommentEvent(message));
    }

    @KafkaListener(topics = "${event.utility.pub.topics.follow-create.name}",
            containerFactory = "followCreateListener")
    public void followCreated(FollowCreateEvent message) {
        String event = createEventName("FOLLOW_EVENT", message.getTargetUuid());
        sseService.send(eventProcessService.saveFollowEvent(message));
    }

    @KafkaListener(topics = "${event.utility.pub.topics.like-create.name}",
            containerFactory = "likeListener")
    public void likeCreated(LikeCreateEvent message) {
        String event = createEventName("LIKE_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveLikeEvent(message));
    }

    @KafkaListener(topics = "${event.contest.pub.topics.contest-result.name}",
            containerFactory = "contestWinnerListener")
    public void contestWinnerCreated(ContestVoteResultEvent message) {
        String event = createEventName("CONTEST_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveContestResultEvent(message));
    }

    @KafkaListener(topics = "${event.grade.pub.topics.member-grade-update.name}",
            containerFactory = "gradeChangeListener")
    public void gradeUpdated(MemberGradeUpdateEvent message) {
        String event = createEventName("GRADE_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveMemberGradeEvent(message));
    }

    @KafkaListener(topics = "${event.chat.pub.topics.chatting-create.name}",
            containerFactory = "chattingCreateListener")
    public void chattingCreated(ChattingCreateEvent message) {
        String event = createEventName("CHAT_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveChattingEvent(message));
    }

    @KafkaListener(topics = "${event.report.pub.topics.report-approve.name}",
            containerFactory = "reportApproveListener")
    public void reportCreated(ReportApproveEvent message) {
        String event = createEventName("REPORT_EVENT", message.getMemberUuid());
        sseService.send(eventProcessService.saveReportEvent(message));
    }

    private String createEventName(String event, String memberUuid) {
        return event + "_" + memberUuid;
    }
}
