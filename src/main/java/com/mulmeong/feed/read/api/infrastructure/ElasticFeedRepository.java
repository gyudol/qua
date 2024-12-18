package com.mulmeong.feed.read.api.infrastructure;

import com.mulmeong.feed.read.api.domain.document.ElasticFeed;
import java.util.Optional;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticFeedRepository extends ElasticsearchRepository<ElasticFeed, String> {

    Optional<ElasticFeed> findByFeedUuid(String feedUuid);

    void deleteByFeedUuid(String feedUuid);

}
