package com.mulmeong.contest.read.infrastructure;

import com.mulmeong.contest.read.entity.ContestPost;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ContestPostRepository extends MongoRepository<ContestPost, String>{
}
