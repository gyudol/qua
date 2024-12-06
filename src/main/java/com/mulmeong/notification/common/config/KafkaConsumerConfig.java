package com.mulmeong.notification.common.config;

import com.mulmeong.event.chat.ChattingCreateEvent;
import com.mulmeong.event.contents.*;
import com.mulmeong.event.contest.ContestVoteResultEvent;
import com.mulmeong.event.member.FollowCreateEvent;
import com.mulmeong.event.member.MemberGradeUpdateEvent;
import com.mulmeong.event.report.ReportApproveEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${event.notification.sub.group-id}")
    private String groupId;

    //피드 작성한 회원을 팔로우하는 사람들의 uuid 목록
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCreatedFollowersEvent> feedCreateFollowersListener() {
        return kafkaListenerContainerFactory(FeedCreatedFollowersEvent.class);
    }

    //쇼츠 작성한 회원을 팔로우하는 사람들의 uuid 목록
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCreatedFollowersEvent> shortCreateFollowersListener() {
        return kafkaListenerContainerFactory(ShortsCreatedFollowersEvent.class);
    }

    //피드 댓글 생성
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedCommentCreateEvent> feedCommentCreateListener() {
        return kafkaListenerContainerFactory(FeedCommentCreateEvent.class);
    }

    //피드 대댓글 생성
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedRecommentCreateEvent> feedRecommentCreateListener() {
        return kafkaListenerContainerFactory(FeedRecommentCreateEvent.class);
    }

    //쇼츠 댓글 생성
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsCommentCreateEvent> shortsCommentCreateListener() {
        return kafkaListenerContainerFactory(ShortsCommentCreateEvent.class);
    }

    //쇼츠 대댓글
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShortsRecommentCreateEvent> shortsRecommentCreateListener() {
        return kafkaListenerContainerFactory(ShortsRecommentCreateEvent.class);
    }

    //팔로우
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FollowCreateEvent> followCreateListener() {
        return kafkaListenerContainerFactory(FollowCreateEvent.class);
    }

    //좋아요
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LikeCreateEvent> likeListener() {
        return kafkaListenerContainerFactory(LikeCreateEvent.class);
    }

    //콘테스트 우승
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ContestVoteResultEvent> contestWinnerListener() {
        return kafkaListenerContainerFactory(ContestVoteResultEvent.class);
    }

    //등급 변경
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberGradeUpdateEvent> gradeChangeListener() {
        return kafkaListenerContainerFactory(MemberGradeUpdateEvent.class);
    }

    //유저 채팅
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChattingCreateEvent> chattingCreateListener() {
        return kafkaListenerContainerFactory(ChattingCreateEvent.class);
    }

    //신고 생성
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReportApproveEvent> reportApproveListener() {
        return kafkaListenerContainerFactory(ReportApproveEvent.class);
    }

    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, messageType.getName());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    public <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(Class<T> messageType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(messageType));
        factory.getContainerProperties().setGroupId(groupId);
        return factory;
    }
}