package com.mulmeong.notification.dto;

import com.mulmeong.notification.document.NotificationStatus;
import com.mulmeong.notification.document.NotificationType;
import lombok.Builder;
import lombok.Getter;

import javax.management.Notification;

@Getter
@Builder
public class NotificationStatusRequestDto {

    private NotificationType notificationType;
    private String memberUuid;
    private boolean notificationStatus;

    public static NotificationStatusRequestDto toDto(
            NotificationType notificationType, String memberUuid) {
        return NotificationStatusRequestDto.builder()
                .notificationType(notificationType)
                .memberUuid(memberUuid)
                .build();
    }

    public NotificationStatus toDocument() {
        return NotificationStatus.builder()
                .notificationType(notificationType)
                .memberUuid(memberUuid)
                .notificationStatus(true)
                .build();
    }

}
