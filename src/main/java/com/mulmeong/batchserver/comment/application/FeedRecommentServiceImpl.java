package com.mulmeong.batchserver.comment.application;

import com.mulmeong.batchserver.comment.domain.document.FeedRecomment;
import com.mulmeong.batchserver.comment.infrastructure.repository.FeedRecommentReadRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.DislikesRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.LikesRepository;
import com.mulmeong.event.utility.consume.DislikesCreateEvent;
import com.mulmeong.event.utility.consume.LikesCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedRecommentServiceImpl implements FeedRecommentService {

    private final FeedRecommentReadRepository feedRecommentReadRepository;
    private final LikesRepository likesRepository;
    private final DislikesRepository dislikesRepository;

    @Override
    public void likeCountRenew(LikesCreateEvent message) {

        FeedRecomment feedRecommentReadUpdate = feedRecommentReadRepository
                .findByRecommentUuid(message.getKindUuid()).orElseThrow();
        Long count = likesRepository.countByKindAndKindUuidAndStatus(message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        feedRecommentReadRepository.save(message.toFeedRecommentReadEntity(feedRecommentReadUpdate, count));

    }

    @Override
    public void dislikeCountRenew(DislikesCreateEvent message) {

        FeedRecomment feedRecommentReadUpdate = feedRecommentReadRepository
                .findByRecommentUuid(message.getKindUuid()).orElseThrow();
        Long count = dislikesRepository.countByKindAndKindUuidAndStatus(message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        feedRecommentReadRepository.save(message.toFeedRecommentReadEntity(feedRecommentReadUpdate, count));

    }

}
