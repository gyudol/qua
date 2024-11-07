package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeedBookmarkMongoRepository extends MongoRepository<FeedBookmarkEntity, String> {
    boolean existsByMemberUuidAndFeedUuid(String memberUuid, String bookmarkUuid);
}
