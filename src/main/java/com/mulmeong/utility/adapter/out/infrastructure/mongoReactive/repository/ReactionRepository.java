package com.mulmeong.utility.adapter.out.infrastructure.mongoReactive.repository;

import com.mulmeong.utility.application.port.out.ReactionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReactionRepository implements ReactionPort {

    private final ReactionReactiveMongoRepository reactionReactiveMongoRepository;

}
