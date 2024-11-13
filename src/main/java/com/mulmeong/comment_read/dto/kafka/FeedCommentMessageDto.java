package com.mulmeong.comment_read.dto.kafka;

import com.mulmeong.comment_read.entity.FeedComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class FeedCommentMessageDto {

    private String feedUuid;
    private String memberUuid;
    private String commentUuid;
    private String content;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;


    public FeedCommentMessageDto(FeedCommentMessageDto messageDto) {
        this.feedUuid = messageDto.getFeedUuid();
        this.memberUuid = messageDto.getMemberUuid();
        this.commentUuid = messageDto.getCommentUuid();
        this.content = messageDto.getContent();
        this.status = messageDto.isStatus();
        this.createdAt = messageDto.getCreatedAt();
        this.updatedAt = messageDto.getUpdatedAt();
        this.likeCount = messageDto.getLikeCount();
        this.dislikeCount = messageDto.getDislikeCount();
    }


    public FeedComment toFeedComment() {
        return FeedComment.builder()
                .feedUuid(feedUuid)
                .memberUuid(memberUuid)
                .commentUuid(commentUuid)
                .content(content)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .likeCount(likeCount)
                .dislikeCount(dislikeCount)
                .build();
    }

}
