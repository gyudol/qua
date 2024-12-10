package com.mulmeong.contest.infrastructure;

import com.mulmeong.contest.domain.document.ContestPostMedia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostMediaRepository extends MongoRepository<ContestPostMedia, String> {
}
