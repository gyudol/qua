package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.document.FeedHashtag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedHashtagRepository extends MongoRepository<FeedHashtag, String> {

    void deleteAllByFeedUuid(String feedUuid);

}
