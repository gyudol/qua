package com.mulmeong.batchserver.utility.application;

import com.mulmeong.event.utility.consume.FeedCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilityKafkaConsumer {

    private final FollowService followService;

    @KafkaListener(topics = "${event.feed.pub.topics.feed-create.name}",
            containerFactory = "feedCreateListener")
    public void feedCreated(FeedCreateEvent message) {
        log.info("feed create message: {}", message.getFeedUuid());
        followService.createFeedFollowerAlert(message);
    }

}
