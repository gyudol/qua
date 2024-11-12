package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {

}
