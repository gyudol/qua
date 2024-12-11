package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.common.utils.CursorPage;
import com.mulmeong.utility.domain.model.FeedBookmark;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBookmarkMongoRepositoryCustom {

    CursorPage<FeedBookmarkEntity> getFeedBookmarks(
            String memberUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo);
}
