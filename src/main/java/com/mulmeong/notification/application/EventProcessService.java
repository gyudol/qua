package com.mulmeong.notification.application;

import com.mulmeong.event.chat.ChattingCreateEvent;
import com.mulmeong.event.contents.*;
import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberGradeUpdateEvent;
import com.mulmeong.event.contest.ContestVoteResultEvent;
import com.mulmeong.event.member.FollowCreateEvent;
import com.mulmeong.event.report.ReportApproveEvent;
import com.mulmeong.notification.document.NotificationHistory;

import java.util.List;

public interface EventProcessService {
    void saveMemberNotificationStatus(MemberCreateEvent message);

    void saveFeedEvent(FeedCreatedFollowersEvent message);

    void saveShortsEvent(ShortsCreatedFollowersEvent message);

    void saveFeedCommentEvent(FeedCommentCreateEvent message);

    void saveFeedRecommentEvent(FeedRecommentCreateEvent message);

    void saveShortsCommentEvent(ShortsCommentCreateEvent message);

    void saveShortsRecommentEvent(ShortsRecommentCreateEvent message);

    void saveLikeEvent(LikeCreateEvent message);

    void saveFollowEvent(FollowCreateEvent message);

    void saveChattingEvent(ChattingCreateEvent message);

    void saveContestResultEvent(ContestVoteResultEvent message);

    void saveMemberGradeEvent(MemberGradeUpdateEvent message);

    void saveReportEvent(ReportApproveEvent message);


}
