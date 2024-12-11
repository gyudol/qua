package com.mulmeong.contest.read.infrastructure;

import com.mulmeong.contest.read.domain.document.ContestPost;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@Repository
public interface ContestPostRepository extends MongoRepository<ContestPost, String> {
    Optional<ContestPost> findByPostUuid(String postUuid);
}
