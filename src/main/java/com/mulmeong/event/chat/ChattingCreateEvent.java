package com.mulmeong.event.chat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class ChattingCreateEvent {
    private String memberUuid;
    private String targetUuid;
    private String chatRoomUuid;
    private String message;
    private LocalDateTime createdAt;
}
