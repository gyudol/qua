package com.mulmeong.notification.dto;

import com.mulmeong.notification.client.member.MemberDto;
import com.mulmeong.notification.document.NotificationComment;
import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeResponseDto {
    private String notificationHistoryUuid;
    private String targetUuid; //notification 받는 사람
    private NotificationType notificationType; //notification 종류 id
    private String kindUuid;
    private String linkToUuid;
    private String comment; //알림 comment
    private String content;
    private String sourceType; //user or admin
    private String sourceUuid;
    private boolean isRead;
    private LocalDateTime createdAt;
    private String sourceNickname;
    private String sourceProfileImage;

    public static NoticeResponseDto toDto(NotificationHistory notificationHistory, MemberDto memberDto) {
        return NoticeResponseDto.builder()
                .notificationHistoryUuid(notificationHistory.getNotificationHistoryUuid())
                .notificationType(notificationHistory.getNotificationType())
                .targetUuid(notificationHistory.getTargetUuid())
                .comment(notificationHistory.getComment())
                .createdAt(notificationHistory.getCreatedAt())
                .content(notificationHistory.getContent())
                .kindUuid(notificationHistory.getKindUuid())
                .linkToUuid(notificationHistory.getLinkToUuid())
                .sourceUuid(memberDto.getMemberUuid())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }
}
