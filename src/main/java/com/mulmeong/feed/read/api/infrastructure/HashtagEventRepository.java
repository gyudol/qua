package com.mulmeong.feed.read.api.infrastructure;

import com.mulmeong.feed.read.api.domain.entity.FeedHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagEventRepository extends JpaRepository<FeedHashtag, Long> {

}
