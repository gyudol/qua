package com.mulmeong.comment.read.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedCommentResponseVo {

    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private String content;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer recommentCount;
}
