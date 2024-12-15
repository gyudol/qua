package com.mulmeong.batchserver.comment.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "feed_comment")
public class FeedComment {
    @Id
    private String id;
    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private String content;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long likeCount;
    private Long dislikeCount;
    private Long recommentCount;
    private String customCursor;
}
