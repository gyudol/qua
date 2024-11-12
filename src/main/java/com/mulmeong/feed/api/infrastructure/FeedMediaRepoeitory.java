package com.mulmeong.feed.api.infrastructure;

import com.mulmeong.feed.api.domain.FeedMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedMediaRepoeitory extends JpaRepository<FeedMedia, Long> {

}
