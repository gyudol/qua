package com.mulmeong.notification.vo;

import com.mulmeong.notification.document.NotificationType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationHistoryResponseVo {
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
}
