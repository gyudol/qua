package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.FeedMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedMediaRepository extends JpaRepository<FeedMedia, Long> {

    void deleteAllByFeedUuid(String feedUuid);

}
