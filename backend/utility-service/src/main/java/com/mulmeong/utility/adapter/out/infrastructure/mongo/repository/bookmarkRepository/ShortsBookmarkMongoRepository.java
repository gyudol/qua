package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.ShortsBookmarkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShortsBookmarkMongoRepository extends MongoRepository<ShortsBookmarkEntity, String> {

    boolean existsByMemberUuidAndShortsUuid(String memberUuid, String bookmarkUuid);

    void deleteByMemberUuidAndShortsUuid(String memberUuid, String feedUuid);

}
