package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.ReactionRequestDto;
import com.mulmeong.utility.domain.model.Reaction;
import reactor.core.publisher.Mono;

public interface ReactionUseCase {
    Mono<Reaction> likes(ReactionRequestDto reactionRequestDto);
}
