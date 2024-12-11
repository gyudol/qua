package com.mulmeong.notification.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "notification_status")
public class NotificationStatus {
    @Id
    private String id;
    private NotificationType notificationType;
    private String memberUuid;
    private boolean notificationStatus;
}
