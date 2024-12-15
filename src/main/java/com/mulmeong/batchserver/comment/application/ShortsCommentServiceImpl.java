package com.mulmeong.batchserver.comment.application;

import com.mulmeong.batchserver.comment.domain.document.ShortsComment;
import com.mulmeong.batchserver.comment.infrastructure.repository.ShortsCommentReadRepository;
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
public class ShortsCommentServiceImpl implements ShortsCommentService {

    private final ShortsCommentReadRepository shortsCommentReadRepository;
    private final LikesRepository likesRepository;
    private final DislikesRepository dislikesRepository;

    @Override
    public void likeCountRenew(LikeRenewCreateEvent message) {

        ShortsComment shortsReadUpdate = shortsCommentReadRepository
                .findByCommentUuid(message.getKindUuid()).orElseThrow();
        Long count = likesRepository.countByKindAndKindUuidAndStatus(message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        shortsCommentReadRepository.save(message.toShortsCommentReadEntity(shortsReadUpdate, count));

    }

    @Override
    public void dislikeCountRenew(DislikeRenewCreateEvent message) {

        ShortsComment shortsReadUpdate = shortsCommentReadRepository
                .findByCommentUuid(message.getKindUuid()).orElseThrow();
        Long count = dislikesRepository.countByKindAndKindUuidAndStatus(message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        shortsCommentReadRepository.save(message.toShortsCommentReadEntity(shortsReadUpdate, count));

    }
}
