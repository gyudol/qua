package com.mulmeong.comment_read.infrsatructure;

import com.mulmeong.comment_read.entity.FeedComment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedCommentRepository extends MongoRepository<FeedComment, String> {
}
