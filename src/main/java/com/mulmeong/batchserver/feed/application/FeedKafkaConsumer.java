package com.mulmeong.batchserver.feed.application;

import com.mulmeong.batchserver.utility.application.FollowService;
import com.mulmeong.event.utility.consume.FeedCreateEvent;
import com.mulmeong.event.utility.consume.LikesCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedKafkaConsumer {

    private final FeedService feedService;

    @KafkaListener(topics = "${event.utility.pub.topics.like-create.name}",
            containerFactory = "likeCreateListener")
    public void feedCreated(LikesCreateEvent message) {
        log.info("feed create message: {}", message.getKindUuid());
        feedService.likeCountRenew(message);
    }

}
