package com.mulmeong.event.comment;

import com.mulmeong.comment.read.entity.ShortsComment;
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
    private boolean status;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public ShortsComment toEntity() {
        return ShortsComment.builder()
                .shortsUuid(shortsUuid)
                .memberUuid(memberUuid)
                .commentUuid(commentUuid)
                .content(content)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .likeCount(likeCount)
                .dislikeCount(dislikeCount)
                .recommentCount(0)
                .build();
    }

}
