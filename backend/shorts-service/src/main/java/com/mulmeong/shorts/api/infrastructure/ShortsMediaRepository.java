package com.mulmeong.shorts.api.infrastructure;

import com.mulmeong.shorts.api.domain.document.ShortsMedia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortsMediaRepository extends MongoRepository<ShortsMedia, String> {

    void deleteByShortsUuid(String shortsUuid);

}
