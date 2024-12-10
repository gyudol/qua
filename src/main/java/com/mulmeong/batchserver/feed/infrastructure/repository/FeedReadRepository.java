package com.mulmeong.batchserver.feed.infrastructure.repository;

import com.mulmeong.batchserver.feed.domain.document.FeedRead;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeedReadRepository extends MongoRepository<FeedRead, String> {
    Optional<FeedRead> findByFeedUuid(String feedUuid);
}
