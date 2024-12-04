package com.mulmeong.contest.application;

import com.mulmeong.event.contest.consume.ContestStatusEvent;
import com.mulmeong.event.contest.consume.ContestVoteResultEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final ContestService contestService;

    @KafkaListener(topics = "${event.contest.pub.topics.contest-result.name}",
            containerFactory = "contestVoteResultListener")
    public void createContestResult(ContestVoteResultEvent message) {
        log.info("vote message: {}", message.toString());
        contestService.createContestResult(message);
    }

    @KafkaListener(topics = "${event.contest.pub.topics.contest-status-alter.name}",
            containerFactory = "contestStatusListener")
    public void altContestStatus(ContestStatusEvent message) {
        log.info("result message: {}", message.toString());
        contestService.altContestStatus(message);
    }

}
