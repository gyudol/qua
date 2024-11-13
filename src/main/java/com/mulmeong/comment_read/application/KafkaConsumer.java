package com.mulmeong.comment_read.application;

import com.mulmeong.comment_read.dto.kafka.FeedCommentMessageDto;
import com.mulmeong.comment_read.entity.FeedComment;
import com.mulmeong.comment_read.infrsatructure.FeedCommentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {
    private final FeedCommentRepository feedCommentRepository;

    public KafkaConsumer(FeedCommentRepository feedCommentRepository) {
        this.feedCommentRepository = feedCommentRepository;
    }

    @KafkaListener(topics = "comment-events", groupId = "kafka-read")
    public void processMessage(FeedCommentMessageDto messageDto) {
        System.out.println("New comment saved to MongoDB: " + messageDto);
        FeedComment feedComment = new FeedCommentMessageDto(messageDto).toFeedComment();
        System.out.println(feedComment.toString());
        feedCommentRepository.save(feedComment);
    }

}
