//package com.mulmeong.comment.application;
//
//import com.fasterxml.jackson.databind.ser.std.StringSerializer;
//import com.mulmeong.comment.dto.kafka.FeedCommentMessageDto;
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RequiredArgsConstructor
//@Service
//public class KafkaProducer {
//    private static final String TOPIC = "comment-events";
//
//    private final KafkaTemplate<String, FeedCommentMessageDto> kafkaTemplate;
//
//    public void sendFeedCommentMessage(FeedCommentMessageDto message) {
//        kafkaTemplate.send(TOPIC, message);
//    }
//
//}
//
