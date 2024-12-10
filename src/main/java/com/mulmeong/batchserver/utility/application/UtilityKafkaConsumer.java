package com.mulmeong.batchserver.utility.application;

import com.mulmeong.batchserver.feed.application.FeedService;
import com.mulmeong.event.utility.consume.FeedCreateEvent;
import com.mulmeong.event.utility.consume.LikesCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilityKafkaConsumer {

    private final FollowService followService;
    private final FeedService feedService;

    @KafkaListener(topics = "${event.feed.pub.topics.feed-create.name}",
            containerFactory = "feedCreateListener")
    public void feedCreated(FeedCreateEvent message) {
        log.info("feed create message: {}", message.getFeedUuid());
        followService.createFeedFollowerAlert(message);
    }

    @KafkaListener(topics = "${event.utility.pub.topics.like-create.name}",
            containerFactory = "likeCreateListener")
    public void feedLikesCreated(LikesCreateEvent message) {
        String kind = message.getKind();
        log.info("like message: {}", kind);
        switch (kind) {
            case "FEED":
                feedService.likeCountRenew(message);
                break;
            case "SHORTS":
                break;
            case "COMMENT":
                break;
            case "RECOMMENT":
                break;
        }
    }

}
