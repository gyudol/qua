package com.mulmeong.notification.dto;

import com.mulmeong.notification.document.NotificationStatus;
import com.mulmeong.notification.document.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateNotificationStatus {

    private NotificationType notificationType;
    private String memberUuid;

    public static UpdateNotificationStatus toUpdate(NotificationStatus notificationStatus) {
        return UpdateNotificationStatus.builder()
                .notificationType(notificationStatus.getNotificationType())
                .memberUuid(notificationStatus.getMemberUuid())
                .build();
    }

    public NotificationStatus updateStatus(boolean status) {
        return NotificationStatus.builder()
                .notificationType(notificationType)
                .memberUuid(memberUuid)
                .notificationStatus(status)
                .build();
    }
}
