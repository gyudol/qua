package com.mulmeong.comment_read.infrsatructure;

import com.mulmeong.comment_read.entity.FeedRecomment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedRecommentRepository extends MongoRepository<FeedRecomment, String> {
}
