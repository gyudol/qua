package com.mulmeong.notification.vo;

import com.mulmeong.notification.document.NotificationType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationHistoryResponseVo {
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
}
