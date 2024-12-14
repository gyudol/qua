package com.mulmeong.notification.dto;

import com.mulmeong.notification.document.NotificationStatus;
import com.mulmeong.notification.document.NotificationType;
import com.mulmeong.notification.vo.NotificationHistoryResponseVo;
import com.mulmeong.notification.vo.NotificationStatusResponseVo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NotificationStatusResponseDto {
    private NotificationType notificationType;
    private String memberUuid;
    private boolean notificationStatus;

    public static NotificationStatusResponseDto toDto(NotificationStatus notificationStatus) {
        return NotificationStatusResponseDto.builder()
                .notificationType(notificationStatus.getNotificationType())
                .memberUuid(notificationStatus.getMemberUuid())
                .notificationStatus(notificationStatus.isNotificationStatus())
                .build();
    }

    public NotificationStatusResponseVo toVo() {
        return NotificationStatusResponseVo.builder()
                .notificationType(notificationType)
                .memberUuid(memberUuid)
                .notificationStatus(notificationStatus)
                .build();
    }
}
