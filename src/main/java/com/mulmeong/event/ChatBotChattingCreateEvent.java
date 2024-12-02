package com.mulmeong.event;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatBotChattingCreateEvent {
    private String memberUuid;
    private String character;
    private String role;
    private String message;
    private LocalDateTime createdAt;
}

