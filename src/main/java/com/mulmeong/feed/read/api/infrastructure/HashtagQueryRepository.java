package com.mulmeong.feed.read.api.infrastructure;

import com.mulmeong.feed.read.api.domain.entity.FeedHashtag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HashtagQueryRepository extends JpaRepository<FeedHashtag, Long> {

    @Query(value = "SELECT * FROM hashtag ORDER BY RAND() LIMIT :size", nativeQuery = true)
    List<FeedHashtag> getFeedHashtags(int size);

}
