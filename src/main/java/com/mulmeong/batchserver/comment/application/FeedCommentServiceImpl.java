package com.mulmeong.batchserver.comment.application;

import com.mulmeong.batchserver.comment.domain.document.FeedComment;
import com.mulmeong.batchserver.comment.infrastructure.repository.FeedCommentReadRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.DislikesRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.LikesRepository;
import com.mulmeong.event.utility.consume.DislikeRenewCreateEvent;
import com.mulmeong.event.utility.consume.LikeRenewCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedCommentServiceImpl implements FeedCommentService {

    private final FeedCommentReadRepository feedCommentReadRepository;
    private final LikesRepository likesRepository;
    private final DislikesRepository dislikesRepository;

    @Override
    public void likeCountRenew(LikeRenewCreateEvent message) {

        FeedComment feedReadUpdate = feedCommentReadRepository.findByCommentUuid(message.getKindUuid()).orElseThrow();
        log.info("feedUuid: {}", feedReadUpdate.getFeedUuid());
        Long count = likesRepository.countByKindAndKindUuidAndStatus(
                message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        feedCommentReadRepository.save(message.toFeedCommentReadEntity(feedReadUpdate, count));

    }

    @Override
    public void dislikeCountRenew(DislikeRenewCreateEvent message) {

        FeedComment feedReadUpdate = feedCommentReadRepository.findByCommentUuid(message.getKindUuid()).orElseThrow();
        Long count = dislikesRepository.countByKindAndKindUuidAndStatus(
                message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        feedCommentReadRepository.save(message.toFeedCommentReadEntity(feedReadUpdate, count));

    }
}
