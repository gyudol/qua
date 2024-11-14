package com.mulmeong.comment_read.dto.kafka;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mulmeong.comment_read.entity.FeedComment;
import com.mulmeong.comment_read.entity.FeedRecomment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class MessageDto {

    private String contentType;
    private String contentUuid;
    private String memberUuid;
    private String commentUuid;
    private String recommentUuid;
    private boolean status;
    private String content;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public MessageDto(MessageDto messageDto) {

        this.contentType = messageDto.getContentType();
        this.memberUuid = messageDto.getMemberUuid();
        this.commentUuid = messageDto.getCommentUuid();
        this.recommentUuid = messageDto.getRecommentUuid();
        this.status = messageDto.isStatus();
        this.content = messageDto.getContent();
        this.type = messageDto.getType();
        this.createdAt = messageDto.getCreatedAt();
        this.updatedAt = messageDto.getUpdatedAt();
        this.likeCount = messageDto.getLikeCount();
        this.dislikeCount = messageDto.getDislikeCount();
    }

    public FeedComment toFeedComment() {
        return FeedComment.builder()
                .feedUuid(commentUuid)
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

    public FeedRecomment toFeedRecomment() {
        return FeedRecomment.builder()
                .memberUuid(memberUuid)
                .commentUuid(commentUuid)
                .recommentUuid(recommentUuid)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .likeCount(likeCount)
                .dislikeCount(dislikeCount)
                .build();
    }
}
