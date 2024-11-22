package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.entity.FeedMedia;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedMediaRepository extends MongoRepository<FeedMedia, Long> {

    List<FeedMedia> findByFeedUuid(String feedUuid);

    void deleteAllByFeedUuid(String feedUuid);

}
