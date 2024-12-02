package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.document.FeedMedia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedMediaRepository extends MongoRepository<FeedMedia, String> {

    void deleteByFeedUuid(String feedUuid);

}
