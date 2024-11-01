package com.mulmeong.utility.adapter.out.infrastructure.mongoReactive.repository;

import com.mulmeong.utility.domain.model.Reaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactionReactiveMongoRepository extends ReactiveMongoRepository<Reaction, String> {
}
