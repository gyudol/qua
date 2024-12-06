package com.mulmeong.notification.application;

import com.mulmeong.event.chat.ChattingCreateEvent;
import com.mulmeong.event.contents.*;
import com.mulmeong.event.member.MemberGradeUpdateEvent;
import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.dto.NotificationHistoryRequestDto;
import com.mulmeong.notification.dto.NotificationStatusRequestDto;
import com.mulmeong.event.contest.ContestVoteResultEvent;
import com.mulmeong.event.member.FollowCreateEvent;
import com.mulmeong.event.report.ReportApproveEvent;
import com.mulmeong.notification.infrastructure.NotificationTypeRepository;
import com.mulmeong.notification.document.NotificationType;
import com.mulmeong.notification.dto.NotificationTypeRequestDto;
import com.mulmeong.notification.infrastructure.NotificationHistoryRepository;
import com.mulmeong.notification.infrastructure.NotificationStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventProcessServiceImpl implements EventProcessService {

    private final NotificationTypeRepository notificationTypeRepository;
    private final NotificationHistoryRepository notificationHistoryRepository;
    private final NotificationStatusRepository notificationStatusRepository;

    @Override
    public NotificationHistory saveFeedEvent(FeedCreatedFollowersEvent message) {
        NotificationType notificationType = findByKind("feed");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .feedToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveShortsEvent(ShortsCreatedFollowersEvent message) {
        NotificationType notificationType = findByKind("shorts");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .shortsToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveFeedCommentEvent(FeedCommentCreateEvent message) {
        NotificationType notificationType = findByKind("comment");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .feedCommentToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveFeedRecommentEvent(FeedRecommentCreateEvent message) {
        NotificationType notificationType = findByKind("recomment");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .feedRecommentToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveShortsCommentEvent(ShortsCommentCreateEvent message) {
        NotificationType notificationType = findByKind("comment");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .shortsCommentToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveShortsRecommentEvent(ShortsRecommentCreateEvent message) {
        NotificationType notificationType = findByKind("recomment");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .shortsRecommentToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveLikeEvent(LikeCreateEvent message) {
        NotificationType notificationType = findByKind("like");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .likeToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveFollowEvent(FollowCreateEvent message) {
        NotificationType notificationType = findByKind("follow");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getTargetUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .followToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveChattingEvent(ChattingCreateEvent message) {
        NotificationType notificationType = findByKind("chat");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .chatToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveContestResultEvent(ContestVoteResultEvent message) {
        NotificationType notificationType = findByKind("contest");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .contestToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveMemberGradeEvent(MemberGradeUpdateEvent message) {
        NotificationType notificationType = findByKind("grade");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .gradeToDto(notificationType.getId(), message)
                .toDocument());
    }

    @Override
    public NotificationHistory saveReportEvent(ReportApproveEvent message) {
        NotificationType notificationType = findByKind("report");
        notificationStatusRepository.save(NotificationStatusRequestDto
                .toDto(notificationType.getId(), message.getMemberUuid())
                .toDocument());
        return notificationHistoryRepository.save(NotificationHistoryRequestDto
                .reportToDto(notificationType.getId(), message)
                .toDocument());
    }

    private NotificationType findByKind(String kind) {
        return notificationTypeRepository.findByKind(kind)
                .orElseGet(() -> notificationTypeRepository.save(NotificationTypeRequestDto.toDto(kind).toDocument()));
    }
}
