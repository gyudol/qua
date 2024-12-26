package com.mulmeong.shorts.api.infrastructure;

import com.mulmeong.shorts.api.domain.document.ShortsHashtag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortsHashtagRepository extends MongoRepository<ShortsHashtag, String> {

}
