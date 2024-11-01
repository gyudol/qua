package com.mulmeong.utility.application.service;

import com.mulmeong.utility.application.mapper.ReactionDtoMapper;
import com.mulmeong.utility.application.port.in.ReactionUseCase;
import com.mulmeong.utility.application.port.in.dto.ReactionRequestDto;
import com.mulmeong.utility.application.port.out.ReactionPort;
import com.mulmeong.utility.domain.model.Reaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReactionService implements ReactionUseCase {

    private final ReactionPort reactionPort;
    private final ReactionDtoMapper reactionDtoMapper;

    @Override
    public Mono<Reaction> likes(ReactionRequestDto reactionRequestDto) {

        return reactionPort.likes(reactionDtoMapper.toReactionDto(Reaction.builder()
                .memberUuid(reactionRequestDto.getMemberUuid())
                .kind(reactionRequestDto.getKind())
                .kindUuid(reactionRequestDto.getKindUuid())
                .status(1)
                .build()));
    }
}
