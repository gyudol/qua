package com.mulmeong.comment.read.infrsatructure;

import com.mulmeong.comment.read.entity.FeedRecomment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedRecommentRepository extends MongoRepository<FeedRecomment, String> {
    Optional<FeedRecomment> findByRecommentUuid(String recommentUuid);

    boolean deleteByRecommentUuid(String recommentUuid);

    boolean existsByRecommentUuid(String recommentUuid);
}
