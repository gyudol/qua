package com.mulmeong.contest.read.application;

import com.mulmeong.event.contest.ContestPostCreateEvent;
import com.mulmeong.event.contest.ContestPostUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final ContestPostService contestPostService;

    @KafkaListener(topics = "${event.contest.pub.topics.contest-post-create.name}",
            containerFactory = "contestPostCreateListener")
    public void createContestPost(ContestPostCreateEvent message) {
        log.info("message: {}", message.toString());
        contestPostService.createContestPost(message);
    }

    @KafkaListener(topics = "${event.contest.pub.topics.contest-post-update.name}",
            containerFactory = "contestPostUpdateListener")
    public void updateContestPost(ContestPostUpdateEvent message) {
        log.info("vote message: {}", message.toString());
        contestPostService.updateContestPost(message);
    }


}
