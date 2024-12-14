package com.mulmeong.notification.dto;

import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UpdateNotificationHistory {
    private String id;
    private String notificationHistoryUuid;
    private String targetUuid;
    private NotificationType notificationType;
    private String kindUuid;
    private String comment;
    private String content;
    private String sourceType;
    private String sourceUuid;
    private LocalDateTime createdAt;
    private String sourceNickname;
    private String sourceProfileImage;

    public static UpdateNotificationHistory toUpdate(NotificationHistory notificationHistory) {
        return UpdateNotificationHistory.builder()
                .id(notificationHistory.getId())
                .notificationHistoryUuid(notificationHistory.getNotificationHistoryUuid())
                .targetUuid(notificationHistory.getTargetUuid())
                .notificationType(notificationHistory.getNotificationType())
                .kindUuid(notificationHistory.getKindUuid())
                .comment(notificationHistory.getComment())
                .content(notificationHistory.getContent())
                .sourceType(notificationHistory.getSourceType())
                .createdAt(notificationHistory.getCreatedAt())
                .sourceNickname(notificationHistory.getSourceNickname())
                .sourceProfileImage(notificationHistory.getSourceProfileImage())
                .build();
    }

    public NotificationHistory updateRead() {
        return NotificationHistory.builder()
                .id(id)
                .notificationHistoryUuid(notificationHistoryUuid)
                .targetUuid(targetUuid)
                .notificationType(notificationType)
                .kindUuid(kindUuid)
                .comment(comment)
                .content(content)
                .sourceType(sourceType)
                .createdAt(createdAt)
                .sourceNickname(sourceNickname)
                .sourceProfileImage(sourceProfileImage)
                .isRead(true)
                .build();
    }

}
