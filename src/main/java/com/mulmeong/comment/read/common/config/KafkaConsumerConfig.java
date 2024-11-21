package com.mulmeong.comment.read.common.config;

import java.util.Map;

import com.mulmeong.event.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;


    @Bean
    public Map<String, Object> readConsumerConfigs() {
        return CommonJsonDeserializer.getStringObjectMap(bootstrapServers, groupId);
    }

    //피드 댓글 생성
    @Bean
    public ConsumerFactory<String, FeedCommentCreateEvent> feedCommentCreateConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCommentCreateEvent> feedCommentCreateListener() {
        ConcurrentKafkaListenerContainerFactory<String, FeedCommentCreateEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedCommentCreateConsumerFactory());
        return factory;
    }

    //피드 댓글 수정
    @Bean
    public ConsumerFactory<String, FeedCommentUpdateEvent> feedCommentUpdateConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCommentUpdateEvent> feedCommentUpdateListener() {
        ConcurrentKafkaListenerContainerFactory<String, FeedCommentUpdateEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedCommentUpdateConsumerFactory());
        return factory;
    }

    //피드 댓글 삭제
    @Bean
    public ConsumerFactory<String, FeedCommentDeleteEvent> feedCommentDeleteConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCommentDeleteEvent> feedCommentDeleteListener() {
        ConcurrentKafkaListenerContainerFactory<String, FeedCommentDeleteEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedCommentDeleteConsumerFactory());
        return factory;
    }

    //피드 대댓글 생성
    @Bean
    public ConsumerFactory<String, FeedRecommentCreateEvent> feedRecommentCreateConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedRecommentCreateEvent> feedRecommentCreateListener() {
        ConcurrentKafkaListenerContainerFactory<String, FeedRecommentCreateEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedRecommentCreateConsumerFactory());
        return factory;
    }

    //피드 대댓글 수정
    @Bean
    public ConsumerFactory<String, FeedRecommentUpdateEvent> feedRecommentUpdateConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedRecommentUpdateEvent> feedRecommentUpdateListener() {
        ConcurrentKafkaListenerContainerFactory<String, FeedRecommentUpdateEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedRecommentUpdateConsumerFactory());
        return factory;
    }

    //피드 대댓글 삭제
    @Bean
    public ConsumerFactory<String, FeedRecommentDeleteEvent> feedRecommentDeleteConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedRecommentDeleteEvent> feedRecommentDeleteListener() {
        ConcurrentKafkaListenerContainerFactory<String, FeedRecommentDeleteEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedRecommentDeleteConsumerFactory());
        return factory;
    }


    //쇼츠 댓글 생성
    @Bean
    public ConsumerFactory<String, ShortsCommentCreateEvent> shortsCommentCreateConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCommentCreateEvent> shortsCommentCreateListener() {
        ConcurrentKafkaListenerContainerFactory<String, ShortsCommentCreateEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(shortsCommentCreateConsumerFactory());
        return factory;
    }

    //쇼츠 댓글 수정
    @Bean
    public ConsumerFactory<String, ShortsCommentUpdateEvent> shortsCommentUpdateConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCommentUpdateEvent> shortsCommentUpdateListener() {
        ConcurrentKafkaListenerContainerFactory<String, ShortsCommentUpdateEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(shortsCommentUpdateConsumerFactory());
        return factory;
    }

    //쇼츠 댓글 삭제
    @Bean
    public ConsumerFactory<String, ShortsCommentDeleteEvent> shortsCommentDeleteConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCommentDeleteEvent> shortsCommentDeleteListener() {
        ConcurrentKafkaListenerContainerFactory<String, ShortsCommentDeleteEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(shortsCommentDeleteConsumerFactory());
        return factory;
    }

    //쇼츠 대댓글 생성
    @Bean
    public ConsumerFactory<String, ShortsRecommentCreateEvent> shortsRecommentCreateConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentCreateEvent> shortsRecommentCreateListener() {
        ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentCreateEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(shortsRecommentCreateConsumerFactory());
        return factory;
    }

    //쇼츠 대댓글 수정
    @Bean
    public ConsumerFactory<String, ShortsRecommentUpdateEvent> shortsRecommentUpdateConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentUpdateEvent> shortsRecommentUpdateListener() {
        ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentUpdateEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(shortsRecommentUpdateConsumerFactory());
        return factory;
    }

    //쇼츠 대댓글 삭제
    @Bean
    public ConsumerFactory<String, ShortsRecommentDeleteEvent> shortsRecommentDeleteConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentDeleteEvent> shortsRecommentDeleteListener() {
        ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentDeleteEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(shortsRecommentDeleteConsumerFactory());
        return factory;
    }

    //like
    @Bean
    public ConsumerFactory<String, LikeEvent> likeConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LikeEvent> likeListener() {
        ConcurrentKafkaListenerContainerFactory<String, LikeEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(likeConsumerFactory());
        return factory;
    }

    //dislike
    @Bean
    public ConsumerFactory<String, DislikeEvent> dislikeConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DislikeEvent> dislikeListener() {
        ConcurrentKafkaListenerContainerFactory<String, DislikeEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(dislikeConsumerFactory());
        return factory;
    }

}