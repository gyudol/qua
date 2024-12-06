package com.mulmeong.notification.dto;

import com.mulmeong.event.chat.ChattingCreateEvent;
import com.mulmeong.event.contents.*;
import com.mulmeong.event.contest.ContestVoteResultEvent;
import com.mulmeong.event.member.FollowCreateEvent;
import com.mulmeong.event.member.MemberGradeUpdateEvent;
import com.mulmeong.event.report.ReportApproveEvent;
import com.mulmeong.notification.document.NotificationComment;
import com.mulmeong.notification.document.NotificationHistory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationHistoryRequestDto {
    private String notificationId;
    private String memberUuid;
    private String kindUuid;
    private NotificationComment comment;
    private boolean isRead;
    private LocalDateTime createdAt;

    public NotificationHistory toDocument() {
        return NotificationHistory.builder()
                .notificationId(notificationId)
                .memberUuid(memberUuid)
                .kindUuid(kindUuid)
                .comment(comment.getComment())

                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static NotificationHistoryRequestDto feedToDto(
            String notificationId, FeedCreatedFollowersEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getFeedUuid())
                .comment(NotificationComment.FEED_CREATE)
                .build();
    }

    public static NotificationHistoryRequestDto shortsToDto(
            String notificationId, ShortsCreatedFollowersEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getShortsUuid())
                .comment(NotificationComment.SHORTS_CREATE)
                .build();
    }

    public static NotificationHistoryRequestDto feedCommentToDto(
            String notificationId, FeedCommentCreateEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getCommentUuid())
                .comment(NotificationComment.FEED_COMMENT_CREATE)
                .build();
    }

    public static NotificationHistoryRequestDto feedRecommentToDto(
            String notificationId, FeedRecommentCreateEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getRecommentUuid())
                .comment(NotificationComment.FEED_COMMENT_CREATE)
                .build();
    }

    public static NotificationHistoryRequestDto shortsCommentToDto(
            String notificationId, ShortsCommentCreateEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getCommentUuid())
                .comment(NotificationComment.SHORTS_COMMENT_CREATE)
                .build();
    }

    public static NotificationHistoryRequestDto shortsRecommentToDto(
            String notificationId, ShortsRecommentCreateEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getCommentUuid())
                .comment(NotificationComment.SHORTS_COMMENT_CREATE)
                .build();
    }

    public static NotificationHistoryRequestDto likeToDto(
            String notificationId, LikeCreateEvent message) {
        NotificationComment comment = switch (message.getKind()) {
            case "feed" -> NotificationComment.FEED_LIKE_CREATE;
            case "shorts" -> NotificationComment.SHORTS_LIKE_CREATE;
            case "comment", "recomment" -> NotificationComment.COMMENT_LIKE_CREATE;
            default -> null;
        };
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getKindUuid())
                .comment(comment)
                .build();
    }

    public static NotificationHistoryRequestDto followToDto(
            String notificationId, FollowCreateEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getTargetUuid())
                .notificationId(notificationId)
                .kindUuid(message.getSourceUuid())
                .comment(NotificationComment.FOLLOW_CREATE)
                .build();
    }

    public static NotificationHistoryRequestDto gradeToDto(
            String notificationId, MemberGradeUpdateEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getGradeId().toString())
                .comment(NotificationComment.GRADE_UPDATE)
                .build();
    }

    public static NotificationHistoryRequestDto chatToDto(
            String notificationId, ChattingCreateEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getTargetUuid())
                .notificationId(notificationId)
                .kindUuid(message.getChatRoomUuid())
                .comment(NotificationComment.CHAT_CREATE)
                .build();
    }

    public static NotificationHistoryRequestDto contestToDto(
            String notificationId, ContestVoteResultEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getContestId().toString())
                .comment(NotificationComment.WIN_CONTEST)
                .build();
    }

    public static NotificationHistoryRequestDto reportToDto(
            String notificationId, ReportApproveEvent message) {
        return NotificationHistoryRequestDto.builder()
                .memberUuid(message.getMemberUuid())
                .notificationId(notificationId)
                .kindUuid(message.getTargetUuid())
                .comment(NotificationComment.REPORT_APPROVE)
                .build();
    }
}


