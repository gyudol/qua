package com.mulmeong.notification.application;

import com.mulmeong.event.chat.ChattingCreateEvent;
import com.mulmeong.event.contents.*;
import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberGradeUpdateEvent;
import com.mulmeong.notification.client.comment.FeedCommentDto;
import com.mulmeong.notification.client.comment.FeedRecommentDto;
import com.mulmeong.notification.client.comment.ShortsCommentDto;
import com.mulmeong.notification.client.comment.ShortsRecommentDto;
import com.mulmeong.notification.client.feed.FeedDto;
import com.mulmeong.notification.client.member.MemberDto;
import com.mulmeong.notification.client.shorts.ShortsDto;
import com.mulmeong.notification.common.exception.BaseException;
import com.mulmeong.notification.common.response.BaseResponseStatus;
import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationStatus;
import com.mulmeong.notification.dto.NotificationHistoryRequestDto;
import com.mulmeong.notification.dto.NotificationStatusRequestDto;
import com.mulmeong.event.contest.ContestVoteResultEvent;
import com.mulmeong.event.member.FollowCreateEvent;
import com.mulmeong.event.report.ReportApproveEvent;
import com.mulmeong.notification.document.NotificationType;
import com.mulmeong.notification.infrastructure.NotificationHistoryRepository;
import com.mulmeong.notification.infrastructure.NotificationStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventProcessServiceImpl implements EventProcessService {

    private final NotificationHistoryRepository notificationHistoryRepository;
    private final NotificationStatusRepository notificationStatusRepository;
    private final FeignService feignService;
    private final SseService sseService;

    @Override
    public void saveMemberNotificationStatus(MemberCreateEvent message) {
        for (NotificationType type : NotificationType.values()) {
            notificationStatusRepository.save(NotificationStatusRequestDto
                    .toDto(type, message.getMemberUuid())
                    .toDocument());
        }
    }

    @Override
    public void saveFeedEvent(FeedCreatedFollowersEvent message) {
        List<NotificationHistory> notificationHistories = saveFeedHistoryEachFollowers(message);
        notificationHistories.forEach(sseService::send);
    }

    @Override
    public void saveShortsEvent(ShortsCreatedFollowersEvent message) {
        List<NotificationHistory> notificationHistories = saveShortsHistoryEachFollowers(message);
        notificationHistories.forEach(sseService::send);
    }

    @Override
    public void saveFeedCommentEvent(FeedCommentCreateEvent message) {
        FeedDto feedDto = feignService.getSingleFeed(message.getFeedUuid());
        String targetUuid = feedDto.getMemberUuid();
        if (targetUuid.equals(message.getMemberUuid())) {
            return;
        }
        if (checkNotificationStatus(targetUuid, NotificationType.COMMENT)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .feedCommentToDto(message, targetUuid, findMemberProfile(message.getMemberUuid()))
                    .toDocument());
            sseService.send(notificationHistory);
        }
    }

    @Override
    public void saveFeedRecommentEvent(FeedRecommentCreateEvent message) {
        FeedCommentDto feedCommentDto = feignService.getFeedComment(message.getCommentUuid());
        String feedUuid = feedCommentDto.getFeedUuid();
        String targetUuid = feedCommentDto.getMemberUuid();
        if (targetUuid.equals(message.getMemberUuid())) {
            return;
        }
        if (checkNotificationStatus(targetUuid, NotificationType.RECOMMENT)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .feedRecommentToDto(message, targetUuid, findMemberProfile(message.getMemberUuid()), feedUuid)
                    .toDocument());
            sseService.send(notificationHistory);
        }
    }

    @Override
    public void saveShortsCommentEvent(ShortsCommentCreateEvent message) {
        ShortsDto shortsDto = feignService.getSingleShorts(message.getShortsUuid());
        String targetUuid = shortsDto.getMemberUuid();
        if (targetUuid.equals(message.getMemberUuid())) {
            return;
        }
        if (checkNotificationStatus(targetUuid, NotificationType.COMMENT)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .shortsCommentToDto(message, targetUuid, findMemberProfile(message.getMemberUuid()))
                    .toDocument());
            sseService.send(notificationHistory);
        }
    }

    @Override
    public void saveShortsRecommentEvent(ShortsRecommentCreateEvent message) {
        ShortsCommentDto shortsCommentDto = feignService.getShortsComment(message.getCommentUuid());
        String shortsUuid = shortsCommentDto.getShortsUuid();
        String targetUuid = shortsCommentDto.getMemberUuid(); //댓글 작성자
        if (targetUuid.equals(message.getMemberUuid())) {
            return;
        }
        if (checkNotificationStatus(targetUuid, NotificationType.RECOMMENT)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .shortsRecommentToDto(message, targetUuid, findMemberProfile(message.getMemberUuid()), shortsUuid)
                    .toDocument());
            sseService.send(notificationHistory);
        }
    }

    @Override
    public void saveLikeEvent(LikeCreateEvent message) {
        String targetUuid = getTargetUuid(message);
        if (checkNotificationStatus(targetUuid, NotificationType.LIKE)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .likeToDto(message, targetUuid, findMemberProfile(message.getMemberUuid()), getLinkToUuid(message))
                    .toDocument());
            sseService.send(notificationHistory);
        }

    }

    @Override
    public void saveFollowEvent(FollowCreateEvent message) {
        if (checkNotificationStatus(message.getTargetUuid(), NotificationType.FOLLOW)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .followToDto(message, findMemberProfile(message.getSourceUuid()))
                    .toDocument());
            sseService.send(notificationHistory);
        }

    }

    @Override
    public void saveChattingEvent(ChattingCreateEvent message) {
        if (checkNotificationStatus(message.getTargetUuid(), NotificationType.CHAT)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .chatToDto(message, findMemberProfile(message.getMemberUuid()))
                    .toDocument());
            sseService.send(notificationHistory);
        }
    }

    @Override
    public void saveContestResultEvent(ContestVoteResultEvent message) {
        if (checkNotificationStatus(message.getMemberUuid(), NotificationType.CONTEST)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .contestToDto(message)
                    .toDocument());
            sseService.send(notificationHistory);
        }
    }

    @Override
    public void saveMemberGradeEvent(MemberGradeUpdateEvent message) {
        if (checkNotificationStatus(message.getMemberUuid(), NotificationType.GRADE)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .gradeToDto(message)
                    .toDocument());
            sseService.send(notificationHistory);
        }
    }

    @Override
    public void saveReportEvent(ReportApproveEvent message) {
        if (checkNotificationStatus(message.getMemberUuid(), NotificationType.REPORT)) {
            NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistoryRequestDto
                    .reportToDto(message)
                    .toDocument());
            sseService.send(notificationHistory);
        }
    }

    private List<NotificationHistory> saveFeedHistoryEachFollowers(FeedCreatedFollowersEvent message) {
        List<String> followerUuids = message.getFollowerUuids();
        MemberDto memberDto = findMemberProfile(message.getMemberUuid());
        return followerUuids.stream()
                .filter(followerUuid -> checkNotificationStatus(followerUuid, NotificationType.FEED))
                .map(followerUuid -> notificationHistoryRepository.save(NotificationHistoryRequestDto
                        .feedToDto(message, followerUuid, memberDto)
                        .toDocument()))
                .collect(Collectors.toList());
    }

    private List<NotificationHistory> saveShortsHistoryEachFollowers(ShortsCreatedFollowersEvent message) {
        List<String> followerUuids = message.getFollowerUuids();
        MemberDto memberDto = findMemberProfile(message.getMemberUuid());
        return followerUuids.stream()
                .filter(followerUuid -> checkNotificationStatus(followerUuid, NotificationType.SHORTS))
                .map(followerUuid -> notificationHistoryRepository.save(NotificationHistoryRequestDto
                        .shortsToDto(message, followerUuid, memberDto)
                        .toDocument()))
                .collect(Collectors.toList());
    }

    private String getTargetUuid(LikeCreateEvent message) {
        return switch (message.getKind()) {
            case "feed" -> feignService.getSingleFeed(message.getKindUuid()).getMemberUuid();
            case "shorts" -> feignService.getSingleShorts(message.getKindUuid()).getMemberUuid();
            case "comment" -> {
                if (feignService.checkExistFeedComment(message.getKindUuid())) {
                    yield feignService.getFeedComment(message.getKindUuid()).getMemberUuid();
                } else {
                    yield feignService.getShortsComment(message.getKindUuid()).getMemberUuid();
                }
            }
            case "recomment" -> {
                if (feignService.checkExistFeedRecomment(message.getKindUuid())) {
                    yield feignService.getFeedRecomment(message.getKindUuid()).getMemberUuid();
                } else {
                    yield feignService.getShortsRecomment(message.getKindUuid()).getMemberUuid();
                }
            }
            default -> null;
        };
    }

    private String getLinkToUuid(LikeCreateEvent message) {
        return switch (message.getKind()) {
            case "feed" -> feignService.getSingleFeed(message.getKindUuid()).getFeedUuid();
            case "shorts" -> feignService.getSingleShorts(message.getKindUuid()).getShortsUuid();
            case "comment" -> {
                if (feignService.checkExistFeedComment(message.getKindUuid())) {
                    FeedCommentDto feedComment = feignService.getFeedComment(message.getKindUuid());
                    yield feedComment.getFeedUuid();
                } else {
                    yield feignService.getShortsComment(message.getKindUuid()).getShortsUuid();
                }
            }
            case "recomment" -> {
                if (feignService.checkExistFeedRecomment(message.getKindUuid())) {
                    FeedRecommentDto feedRecomment = feignService.getFeedRecomment(message.getKindUuid());
                    yield feignService.getFeedComment(feedRecomment.getCommentUuid()).getFeedUuid();
                } else {
                    ShortsRecommentDto shortsRecomment = feignService.getShortsRecomment(message.getKindUuid());
                    yield feignService.getShortsComment(shortsRecomment.getCommentUuid()).getShortsUuid();
                }
            }
            default -> null;
        };
    }

    private boolean checkNotificationStatus(String targetUuid, NotificationType type) {
        NotificationStatus notificationStatus = notificationStatusRepository
                .findByMemberUuidAndNotificationType(targetUuid, type).orElseThrow(
                        () -> new BaseException(BaseResponseStatus.NO_NOTIFICATION_STATUS)
                );
        return notificationStatus.isNotificationStatus();
    }

    MemberDto findMemberProfile(String memberUuid) {
        return feignService.getCompactProfileByUuid(memberUuid);
    }
}
