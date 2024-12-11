package com.mulmeong.notification.dto;

import com.mulmeong.notification.client.member.MemberDto;
import com.mulmeong.notification.document.NotificationComment;
import com.mulmeong.notification.document.NotificationHistory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeResponseDto {
    private String notificationType;
    private String targetMemberUuid;
    private String notificationComment;
    private String content;
    private String notificationTypeUuid;
    private LocalDateTime createdAt;
    private String sourceMemberUuid;
    private String sourceNickname;
    private String sourceProfileImage;

    public static NoticeResponseDto toDto(NotificationHistory notificationHistory, MemberDto memberDto) {
        return NoticeResponseDto.builder()
                .notificationType(notificationHistory.getNotificationType().getKind())
                .targetMemberUuid(notificationHistory.getTargetUuid())
                .notificationComment(notificationHistory.getComment())
                .createdAt(notificationHistory.getCreatedAt())
                .content(notificationHistory.getContent())
                .notificationTypeUuid(notificationHistory.getKindUuid())
                .sourceMemberUuid(memberDto.getMemberUuid())
                .sourceNickname(memberDto.getNickname())
                .sourceProfileImage(memberDto.getProfileImageUrl())
                .build();
    }
}
