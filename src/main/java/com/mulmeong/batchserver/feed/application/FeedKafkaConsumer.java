package com.mulmeong.batchserver.feed.application;

import com.mulmeong.event.utility.consume.LikesCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedKafkaConsumer {
}
