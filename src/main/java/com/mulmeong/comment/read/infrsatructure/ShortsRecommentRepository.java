package com.mulmeong.comment.read.infrsatructure;

import com.mulmeong.comment.read.entity.FeedRecomment;
import com.mulmeong.comment.read.entity.ShortsRecomment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortsRecommentRepository extends MongoRepository<ShortsRecomment, String> {
    Optional<ShortsRecomment> findByRecommentUuid(String recommentUuid);

    boolean deleteByRecommentUuid(String recommentUuid);

    boolean existsByRecommentUuid(String recommentUuid);
}
