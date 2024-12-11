package com.mulmeong.notification.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "notification_history")
public class NotificationHistory {
    @Id
    private String id;
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
    @CreatedDate
    private LocalDateTime createdAt;
    private String sourceNickname;
    private String sourceProfileImage;
}
