package com.mulmeong.contest.read.infrastructure;

import com.mulmeong.contest.read.entity.ContestVote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContestVoteRepository extends MongoRepository<ContestVote, String> {
}
