package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.out.dto.ReactionResponseDto;
import com.mulmeong.utility.domain.model.Reaction;
import reactor.core.publisher.Mono;

public interface ReactionPort {
    Mono<Reaction> likes(ReactionResponseDto reactionResponseDto);
}
