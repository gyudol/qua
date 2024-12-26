package com.mulmeong.feed.read.api.infrastructure;

import com.mulmeong.feed.read.api.domain.document.Feed;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedEventRepository extends MongoRepository<Feed, String> {

    Optional<Feed> findByFeedUuid(String feedUuid);

    void deleteByFeedUuid(String feedUuid);

}
