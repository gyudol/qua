package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.BookmarkEntity;
import com.mulmeong.utility.domain.model.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookmarkMongoRepository extends MongoRepository<BookmarkEntity, String> {
}
