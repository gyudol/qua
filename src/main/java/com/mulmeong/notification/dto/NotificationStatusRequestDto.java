package com.mulmeong.notification.dto;

import com.mulmeong.notification.document.NotificationStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationStatusRequestDto {

    private String notificationId;
    private String memberUuid;
    private boolean notificationStatus;

    public static NotificationStatusRequestDto toDto(
            String notificationId, String memberUuid) {
        return NotificationStatusRequestDto.builder()
                .notificationId(notificationId)
                .memberUuid(memberUuid)
                .build();
    }

    public NotificationStatus toDocument() {
        return NotificationStatus.builder()
                .notificationId(notificationId)
                .memberUuid(memberUuid)
                .notificationStatus(false)
                .build();
    }
}
