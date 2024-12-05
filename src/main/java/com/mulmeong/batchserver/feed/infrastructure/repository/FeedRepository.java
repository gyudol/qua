package com.mulmeong.batchserver.feed.infrastructure.repository;

import com.mulmeong.batchserver.feed.entity.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
