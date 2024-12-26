package com.mulmeong.event.contents;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class FeedCommentCreateEvent {
    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private String content;
    private LocalDateTime createdAt;
}