package com.mulmeong.notification.dto;

import com.mulmeong.event.chat.ChattingCreateEvent;
import com.mulmeong.event.contents.*;
import com.mulmeong.event.contest.ContestVoteResultEvent;
import com.mulmeong.event.member.FollowCreateEvent;
import com.mulmeong.event.member.MemberGradeUpdateEvent;
import com.mulmeong.event.report.ReportApproveEvent;
import com.mulmeong.notification.client.member.MemberDto;
import com.mulmeong.notification.document.NotificationComment;
import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationType;
import lombok.Builder;
import lombok.Getter;
import org.apache.kafka.common.Uuid;

import java.lang.annotation.Target;
import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationHistoryRequestDto {
    private NotificationType notificationType;
    private String targetUuid;
    private String sourceType;
    private String sourceUuid;
    private String kindUuid;
    private String linkToUuid;
    private NotificationComment comment;
    private String content;
    private boolean isRead;
    private String sourceNickname;
    private String sourceProfileImage;

    public NotificationHistory toDocument() {
        return NotificationHistory.builder()
                .notificationHistoryUuid(Uuid.randomUuid().toString())
                .notificationType(notificationType)
                .targetUuid(targetUuid)
                .sourceUuid(sourceUuid)
                .sourceType(sourceType)
                .linkToUuid(linkToUuid)
                .kindUuid(kindUuid)
                .comment(comment.getComment())
                .content(content)
                .isRead(false)
                .sourceNickname(sourceNickname)
                .sourceProfileImage(sourceProfileImage)
                .build();
    }

    public static NotificationHistoryRequestDto feedToDto(
            FeedCreatedFollowersEvent message, String followerUuid, MemberDto memberDto) {
        return NotificationHistoryRequestDto.builder()
                .sourceUuid(message.getMemberUuid()) //피드 생성한 사람
                .sourceType("user")
                .targetUuid(followerUuid) //피드 생성한 사람을 팔로우하는 사람들
                .notificationType(NotificationType.FEED)
                .kindUuid(message.getFeedUuid())
                .linkToUuid(message.getFeedUuid())
                .comment(NotificationComment.FEED_CREATE)
                .content(message.getTitle())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }

    public static NotificationHistoryRequestDto shortsToDto(
            ShortsCreatedFollowersEvent message, String followerUuid, MemberDto memberDto) {
        return NotificationHistoryRequestDto.builder()
                .sourceUuid(message.getMemberUuid()) //쇼츠 생성한 사람
                .sourceType("user")
                .targetUuid(followerUuid) //쇼츠 생성한 사람을 팔로우하는 사람들
                .notificationType(NotificationType.SHORTS)
                .kindUuid(message.getShortsUuid())
                .linkToUuid(message.getShortsUuid())
                .comment(NotificationComment.SHORTS_CREATE)
                .content(message.getTitle())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }

    public static NotificationHistoryRequestDto feedCommentToDto(
            FeedCommentCreateEvent message, String targetUuid, MemberDto memberDto) {
        return NotificationHistoryRequestDto.builder()
                .sourceUuid(message.getMemberUuid()) //피드 댓글 생성한 사람
                .sourceType("user")
                .targetUuid(targetUuid) //피드 생성한 사람
                .notificationType(NotificationType.COMMENT)
                .kindUuid(message.getCommentUuid())
                .linkToUuid(message.getFeedUuid())
                .comment(NotificationComment.FEED_COMMENT_CREATE)
                .content(message.getContent())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }

    public static NotificationHistoryRequestDto feedRecommentToDto(
            FeedRecommentCreateEvent message, String targetUuid, MemberDto memberDto, String getLinkToUuid) {
        return NotificationHistoryRequestDto.builder()
                .sourceUuid(message.getMemberUuid()) //대댓글 작성한 사람
                .sourceType("user")
                .targetUuid(targetUuid)
                .notificationType(NotificationType.RECOMMENT)
                .kindUuid(message.getRecommentUuid())
                .linkToUuid(getLinkToUuid)
                .comment(NotificationComment.RECOMMENT_CREATE)
                .content(message.getContent())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }

    public static NotificationHistoryRequestDto shortsCommentToDto(
            ShortsCommentCreateEvent message, String targetUuid, MemberDto memberDto) {
        return NotificationHistoryRequestDto.builder()
                .sourceUuid(message.getMemberUuid()) //댓글 작성자
                .sourceType("user")
                .targetUuid(targetUuid)
                .notificationType(NotificationType.COMMENT)
                .kindUuid(message.getCommentUuid())
                .linkToUuid(message.getShortsUuid())
                .comment(NotificationComment.SHORTS_COMMENT_CREATE)
                .content(message.getContent())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }

    public static NotificationHistoryRequestDto shortsRecommentToDto(
            ShortsRecommentCreateEvent message, String targetUuid, MemberDto memberDto, String linkToUuid) {
        return NotificationHistoryRequestDto.builder()
                .sourceUuid(message.getMemberUuid()) //대댓글 작성자
                .sourceType("user")
                .targetUuid(targetUuid) //댓글 작성자
                .notificationType(NotificationType.RECOMMENT)
                .kindUuid(message.getRecommentUuid())
                .linkToUuid(linkToUuid)
                .comment(NotificationComment.RECOMMENT_CREATE)
                .content(message.getContent())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }

    public static NotificationHistoryRequestDto likeToDto(
            LikeCreateEvent message, String targetUuid, MemberDto memberDto, String linkToUuid) {
        NotificationComment comment = switch (message.getKind()) {
            case "feed" -> NotificationComment.FEED_LIKE_CREATE;
            case "shorts" -> NotificationComment.SHORTS_LIKE_CREATE;
            case "comment", "recomment" -> NotificationComment.COMMENT_LIKE_CREATE;
            default -> null;
        };
        NotificationType notificationType = switch (message.getKind()) {
            case "feed" -> NotificationType.FEED;
            case "shorts" -> NotificationType.SHORTS;
            case "comment" -> NotificationType.COMMENT;
            case "recomment" -> NotificationType.RECOMMENT;
            default -> null;
        };
        return NotificationHistoryRequestDto.builder()
                .sourceUuid(message.getMemberUuid())//좋아요 한 사람
                .sourceType("user")
                .targetUuid(targetUuid) //피드, 쇼츠, 댓글, 대댓글 작성한 사람
                .notificationType(notificationType)
                .comment(comment)
                .linkToUuid(linkToUuid)
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }

    public static NotificationHistoryRequestDto followToDto(FollowCreateEvent message, MemberDto memberDto) {
        return NotificationHistoryRequestDto.builder()
                .sourceUuid(message.getSourceUuid())
                .sourceType("user")
                .targetUuid(message.getTargetUuid())
                .notificationType(NotificationType.FOLLOW)
                .comment(NotificationComment.FOLLOW_CREATE)
                .linkToUuid(message.getTargetUuid())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }

    public static NotificationHistoryRequestDto gradeToDto(MemberGradeUpdateEvent message) {
        return NotificationHistoryRequestDto.builder()
                .sourceType("admin")
                .targetUuid(message.getMemberUuid())
                .notificationType(NotificationType.GRADE)
                .kindUuid(message.getGradeId().toString())
                .linkToUuid(message.getMemberUuid())
                .comment(NotificationComment.GRADE_UPDATE)
                .build();
    }

    public static NotificationHistoryRequestDto chatToDto(ChattingCreateEvent message, MemberDto memberDto) {
        return NotificationHistoryRequestDto.builder()
                .sourceUuid(message.getMemberUuid())
                .sourceType("user")
                .targetUuid(message.getTargetUuid())
                .notificationType(NotificationType.CHAT)
                .linkToUuid(message.getChatRoomUuid())
                .comment(NotificationComment.CHAT_CREATE)
                .content(message.getMessage())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }

    public static NotificationHistoryRequestDto contestToDto(ContestVoteResultEvent message) {
        return NotificationHistoryRequestDto.builder()
                .sourceType("admin")
                .targetUuid(message.getMemberUuid())
                .notificationType(NotificationType.CONTEST)
                .linkToUuid(message.getContestId().toString())
                .comment(NotificationComment.WIN_CONTEST)
                .build();
    }

    public static NotificationHistoryRequestDto reportToDto(ReportApproveEvent message) {
        return NotificationHistoryRequestDto.builder()
                .sourceType("admin")
                .targetUuid(message.getMemberUuid())
                .notificationType(NotificationType.REPORT)
                .linkToUuid(message.getTargetUuid())
                .comment(NotificationComment.REPORT_APPROVE)
                .build();
    }
}


