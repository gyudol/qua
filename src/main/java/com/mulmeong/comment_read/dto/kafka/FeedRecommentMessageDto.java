//package com.mulmeong.comment_read.dto.kafka;
//
//import com.mulmeong.comment_read.entity.FeedRecomment;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//public class FeedRecommentMessageDto {
//
//    private String memberUuid;
//    private String commentUuid;
//    private String recommentUuid;
//    private String content;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private Integer likeCount;
//    private Integer dislikeCount;
//
//    public FeedRecommentMessageDto(FeedCommentMessageDto messageDto) {
//        this.memberUuid = messageDto.getMemberUuid();
//        this.commentUuid = messageDto.getCommentUuid();
//        this.recommentUuid = messageDto.getRecommentUuid();
//        this.content = messageDto.getContent();
//        this.createdAt = messageDto.getCreatedAt();
//        this.updatedAt = messageDto.getUpdatedAt();
//        this.likeCount = messageDto.getLikeCount();
//        this.dislikeCount = messageDto.getDislikeCount();
//    }
//
//    public FeedRecomment toFeedRecomment() {
//        return FeedRecomment.builder()
//                .memberUuid(memberUuid)
//                .commentUuid(commentUuid)
//                .recommentUuid(recommentUuid)
//                .content(content)
//                .createdAt(createdAt)
//                .updatedAt(updatedAt)
//                .likeCount(likeCount)
//                .dislikeCount(dislikeCount)
//                .build();
//    }
//}
