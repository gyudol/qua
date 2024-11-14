package com.mulmeong.comment_read.application;

import com.mulmeong.comment_read.dto.kafka.MessageDto;
import com.mulmeong.comment_read.entity.FeedComment;
import com.mulmeong.comment_read.entity.FeedRecomment;
import com.mulmeong.comment_read.infrsatructure.FeedCommentRepository;
import com.mulmeong.comment_read.infrsatructure.FeedRecommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {
    private final FeedCommentRepository feedCommentRepository;
    private final FeedRecommentRepository feedRecommentRepository;

    public KafkaConsumer(FeedCommentRepository feedCommentRepository, FeedRecommentRepository feedRecommentRepository) {
        this.feedCommentRepository = feedCommentRepository;
        this.feedRecommentRepository = feedRecommentRepository;
    }

    @KafkaListener(topics = "comment-events", groupId = "kafka-comment-read")
    public void processMessage(MessageDto messageDto) {
        System.out.println("New comment saved to MongoDB: " + messageDto);
        switch (messageDto.getContentType()) {
            case "FEED_COMMENT":
                FeedComment feedComment = new MessageDto(messageDto).toFeedComment();
                feedCommentRepository.save(feedComment);
                break;


            case "FEED_RECOMMENT":
                FeedRecomment feedRecomment = new MessageDto(messageDto).toFeedRecomment();
                feedRecommentRepository.save(feedRecomment);
                break;
        }

    }

//    @KafkaListener(topics = "comment-events", groupId = "kafka-recomment-read")
//    public void processFeedRecommentMessage(FeedRecommentMessageDto messageDto) {
//        System.out.println("New reComment saved to MongoDB: " + messageDto);
//        FeedRecomment feedRecomment = new FeedRecommentMessageDto(messageDto).toFeedRecomment();
//        feedRecommentRepository.save(feedRecomment);
//    }
}
