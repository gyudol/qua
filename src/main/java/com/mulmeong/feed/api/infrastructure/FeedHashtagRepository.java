package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.FeedHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedHashtagRepository extends JpaRepository<FeedHashtag, Long> {

}
