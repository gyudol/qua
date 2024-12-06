package com.mulmeong.notification.dto;

import com.mulmeong.notification.document.NotificationComment;
import com.mulmeong.notification.document.NotificationHistory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeResponseDto {
    private String notificationId;
    private String memberUuid;
    private String content;

    public static NoticeResponseDto toDto(NotificationHistory notificationHistory) {
        return NoticeResponseDto.builder()
                .notificationId(notificationHistory.getNotificationId())
                .memberUuid(notificationHistory.getMemberUuid())
                .content(notificationHistory.getComment())
                .build();
    }
}
