package com.mulmeong.contest.application;

import com.mulmeong.event.contest.ContestVoteRenewEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final ContestService contestService;

    @KafkaListener(topics = "${event.contest.pub.topics.contest-vote-renew.name}",
            containerFactory = "contestVoteRenewListener")
    public void updateContestPost(ContestVoteRenewEvent message) {
        log.info("vote message: {}", message.toString());
        contestService.voteRenew(message);
    }


}
