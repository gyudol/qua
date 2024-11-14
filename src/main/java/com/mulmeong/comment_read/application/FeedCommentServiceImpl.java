package com.mulmeong.comment_read.application;

import com.mulmeong.comment_read.entity.FeedComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedCommentServiceImpl implements FeedCommentService {

    private final KafkaConsumer kafkaConsumer;


}
