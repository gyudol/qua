package com.mulmeong.event;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class ShortsCommentCreateEvent {

    private String shortsUuid;
    private String memberUuid;
    private String commentUuid;
    private boolean isDeleted;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
