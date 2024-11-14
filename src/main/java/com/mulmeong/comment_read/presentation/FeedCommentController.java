package com.mulmeong.comment_read.presentation;

import com.mulmeong.comment_read.application.KafkaConsumer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/v1/comment")
@CrossOrigin(origins = "*")
public class FeedCommentController {

    private KafkaConsumer kafkaConsumer;

}
