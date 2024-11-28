package com.mulmeong.feed.read.api.infrastructure;

import com.mulmeong.feed.read.api.domain.document.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedQueryRepository extends MongoRepository<Feed, String> {

}
