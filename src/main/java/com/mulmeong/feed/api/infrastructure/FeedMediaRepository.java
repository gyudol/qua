package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.document.FeedMedia;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedMediaRepository extends MongoRepository<FeedMedia, String> {

    List<FeedMedia> findByFeedUuid(String feedUuid);

    void deleteByFeedUuid(String feedUuid);

}
