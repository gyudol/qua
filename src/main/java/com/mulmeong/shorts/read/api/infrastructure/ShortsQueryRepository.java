package com.mulmeong.shorts.read.api.infrastructure;

import com.mulmeong.shorts.read.api.domain.document.Shorts;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortsQueryRepository extends MongoRepository<Shorts, String> {

    Optional<Shorts> findByShortsUuid(String shortsUuid);

}
