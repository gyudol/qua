package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.document.FeedHashtag;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedHashtagRepository extends MongoRepository<FeedHashtag, String> {

    Optional<FeedHashtag> findByFeedUuid(String feedUuid);

    void deleteAllByFeedUuid(String feedUuid);

}
