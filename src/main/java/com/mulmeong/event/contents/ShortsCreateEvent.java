package com.mulmeong.event.contents;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class ShortsCreateEvent {
    private String shortsUuid;
    private String memberUuid;
    private String title;
    private LocalDateTime createdAt;
}
