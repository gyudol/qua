//package com.mulmeong.comment_read.dto.kafka;
//
//import com.mulmeong.comment_read.entity.FeedComment;
//import com.mulmeong.comment_read.entity.FeedRecomment;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//
//@Data
//@NoArgsConstructor
//public class FeedCommentMessageDto extends MessageDto {
//
//    private String feedUuid;
//    private boolean status;
//
//    public FeedCommentMessageDto(FeedCommentMessageDto dto) {
//        super(
//                dto.getMemberUuid(),
//                dto.getCommentUuid(),
//                dto.getContent(),
//                dto.getType(),
//                dto.getCreatedAt(),
//                dto.getUpdatedAt(),
//                dto.getLikeCount(),
//                dto.getDislikeCount()
//        );
//        this.feedUuid = dto.getFeedUuid();
//        this.status = dto.isStatus();
//    }
//
//    public FeedComment toFeedComment() {
//        return FeedComment.builder()
//                .feedUuid(feedUuid)
//                .memberUuid(super.getMemberUuid())
//                .commentUuid(super.getCommentUuid())
//                .content(super.getContent())
//                .status(status)
//                .createdAt(super.getCreatedAt())
//                .updatedAt(super.getUpdatedAt())
//                .likeCount(super.getLikeCount())
//                .dislikeCount(super.getDislikeCount())
//                .build();
//    }
//
//}
