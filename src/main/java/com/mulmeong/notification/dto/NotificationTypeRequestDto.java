package com.mulmeong.notification.dto;

import com.mulmeong.notification.document.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationTypeRequestDto {
    private String kind;

    public static NotificationTypeRequestDto toDto(String kind) {
        return NotificationTypeRequestDto.builder()
                .kind(kind)
                .build();
    }

    public NotificationType toDocument() {
        return NotificationType.builder()
                .kind(kind)
                .build();
    }
}
