package com.mulmeong.event.contents;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class ShortsRecommentCreateEvent {
    private String memberUuid;
    private String commentUuid;
    private String recommentUuid;
    private String content;
    private LocalDateTime createdAt;
}
