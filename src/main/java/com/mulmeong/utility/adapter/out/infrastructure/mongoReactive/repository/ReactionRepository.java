package com.mulmeong.utility.adapter.out.infrastructure.mongoReactive.repository;

import com.mulmeong.utility.application.port.out.ReactionPort;
import com.mulmeong.utility.application.port.out.dto.ReactionResponseDto;
import com.mulmeong.utility.domain.model.Reaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Repository
public class ReactionRepository implements ReactionPort {

    private final ReactionReactiveMongoRepository reactionReactiveMongoRepository;

    @Override
    public Mono<Reaction> likes(ReactionResponseDto reactionResponseDto) {
        return reactionReactiveMongoRepository.save(reactionResponseDto.toEntity());
    }
}
