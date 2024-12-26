package com.mulmeong.notification.vo;

import com.mulmeong.notification.document.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NotificationStatusResponseVo {
    private NotificationType notificationType;
    private String memberUuid;
    private boolean notificationStatus;
}
