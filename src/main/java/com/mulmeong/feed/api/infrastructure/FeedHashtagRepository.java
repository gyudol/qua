package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.FeedHashtag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedHashtagRepository extends JpaRepository<FeedHashtag, Long> {

    List<FeedHashtag> findByFeedUuid(String feedUuid);

    void deleteAllByFeedUuid(String feedUuid);

}
