package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.ShortsBookmarkEntity;
import com.mulmeong.utility.common.utils.CursorPage;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortsBookmarkMongoRepositoryCustom {

    CursorPage<ShortsBookmarkEntity> getShortsBookmarks(
            String memberUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo);
}
