package com.mulmeong.batchserver.comment.application;

import com.mulmeong.batchserver.comment.domain.document.ShortsRecomment;
import com.mulmeong.batchserver.comment.infrastructure.repository.ShortsRecommentReadRepository;
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
public class ShortsRecommentServiceImpl implements ShortsRecommentService {

    private final ShortsRecommentReadRepository shortsReommentReadRepository;
    private final LikesRepository likesRepository;
    private final DislikesRepository dislikesRepository;

    @Override
    public void likeCountRenew(LikesCreateEvent message) {

        ShortsRecomment shortsRecommentReadUpdate = shortsReommentReadRepository
                .findByRecommentUuid(message.getKindUuid()).orElseThrow();
        Long count = likesRepository.countByKindAndKindUuidAndStatus(message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        shortsReommentReadRepository.save(message.toShortsRecommentReadEntity(shortsRecommentReadUpdate, count));

    }

    @Override
    public void dislikeCountRenew(DislikesCreateEvent message) {

        ShortsRecomment shortsRecommentReadUpdate = shortsReommentReadRepository
                .findByRecommentUuid(message.getKindUuid()).orElseThrow();
        Long count = dislikesRepository.countByKindAndKindUuidAndStatus(message.getKind(), message.getKindUuid(), true);
        log.info("count: {}", count);
        shortsReommentReadRepository.save(message.toShortsRecommentReadEntity(shortsRecommentReadUpdate, count));

    }
}
