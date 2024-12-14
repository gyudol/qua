package com.mulmeong.notification.dto;

import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationType;
import com.mulmeong.notification.vo.NotificationHistoryResponseVo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationHistoryResponseDto {
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

    public static NotificationHistoryResponseDto toDto(NotificationHistory notificationHistory) {
        return NotificationHistoryResponseDto.builder()
                .notificationHistoryUuid(notificationHistory.getNotificationHistoryUuid())
                .notificationType(notificationHistory.getNotificationType())
                .kindUuid(notificationHistory.getKindUuid())
                .targetUuid(notificationHistory.getTargetUuid())
                .linkToUuid(notificationHistory.getLinkToUuid())
                .createdAt(notificationHistory.getCreatedAt())
                .comment(notificationHistory.getComment())
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
                .kindUuid(kindUuid)
                .createdAt(createdAt)
                .targetUuid(targetUuid)
                .linkToUuid(linkToUuid)
                .comment(comment)
                .content(content)
                .sourceType(sourceType)
                .sourceUuid(sourceUuid)
                .sourceNickname(sourceNickname)
                .sourceProfileImage(sourceProfileImage)
                .isRead(isRead)
                .build();
    }
}
