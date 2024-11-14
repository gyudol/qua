package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.FeedMedia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedMediaRepository extends JpaRepository<FeedMedia, Long> {

    List<FeedMedia> findByFeedUuid(String feedUuid);

    void deleteAllByFeedUuid(String feedUuid);

}
