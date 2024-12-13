package com.mulmeong.notification.client.comment;

import com.mulmeong.notification.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "comment-service", url = "${api.comment-service.url}")
public interface CommentClient {
    @GetMapping("/feeds/comments/{commentUuid}")
    BaseResponse<FeedCommentDto> getFeedComment(@PathVariable String commentUuid);

    @GetMapping("/feeds/comments/exist/{commentUuid}")
    boolean existByFeedCommentUuid(@PathVariable String commentUuid);

    @GetMapping("/feeds/comments/recomments/{recommentUuid}")
    BaseResponse<FeedRecommentDto> getFeedRecomment(@PathVariable String recommentUuid);

    @GetMapping("/feeds/comments/recomments/exist/{recommentUuid}")
    boolean existByFeedRecommentUuid(@PathVariable String recommentUuid);

    @GetMapping("/shorts/comments/{commentUuid}")
    BaseResponse<ShortsCommentDto> getShortsComment(@PathVariable String commentUuid);

    @GetMapping("/shorts/comments/exist/{commentUuid}")
    boolean existByShortsCommentUuid(@PathVariable String commentUuid);

    @GetMapping("/shorts/comments/recomments/{recommentUuid}")
    BaseResponse<ShortsRecommentDto> getShortsRecomment(@PathVariable String recommentUuid);

    @GetMapping("/shorts/comments/recomments/exist/{recommentUuid}")
    boolean existByShortsRecommentUuid(@PathVariable String recommentUuid);

}
