package com.mulmeong.notification.dto;

import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationType;
import com.mulmeong.notification.vo.NotificationHistoryResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationHistoryResponseDto {
    private String notificationHistoryUuid;
    private NotificationType notificationType;
    private String notificationTypeUuid;
    private LocalDateTime createdAt;
    private String notificationComment;
    private String content;
    private String sourceType;
    private String sourceUuid;
    private String sourceNickname;
    private String sourceProfileImage;
    private boolean isRead;

    public static NotificationHistoryResponseDto toDto(NotificationHistory notificationHistory) {
        return NotificationHistoryResponseDto.builder()
                .notificationHistoryUuid(notificationHistory.getNotificationHistoryUuid())
                .notificationType(notificationHistory.getNotificationType())
                .notificationTypeUuid(notificationHistory.getKindUuid())
                .createdAt(notificationHistory.getCreatedAt())
                .notificationComment(notificationHistory.getComment())
                .content(notificationHistory.getContent())
                .sourceType(notificationHistory.getSourceType())
                .sourceUuid(notificationHistory.getSourceUuid())
                .sourceNickname(notificationHistory.getSourceNickname())
                .sourceProfileImage(notificationHistory.getSourceProfileImage())
                .isRead(notificationHistory.isRead())
                .build();
    }

    public NotificationHistoryResponseVo toVo() {
        return NotificationHistoryResponseVo.builder()
                .notificationHistoryUuid(notificationHistoryUuid)
                .notificationType(notificationType)
                .notificationTypeUuid(notificationTypeUuid)
                .createdAt(createdAt)
                .notificationComment(notificationComment)
                .content(content)
                .sourceType(sourceType)
                .sourceUuid(sourceUuid)
                .sourceNickname(sourceNickname)
                .sourceProfileImage(sourceProfileImage)
                .isRead(isRead)
                .build();
    }
}
