package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.Feed;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    Optional<Feed> findByFeedUuidAndMemberUuid(String feedUuid, String memberUuid);

    void deleteByFeedUuidAndMemberUuid(String feedUuid, String memberUuid);

}
