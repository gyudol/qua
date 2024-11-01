package com.mulmeong.utility.application.mapper;

import com.mulmeong.utility.application.port.in.dto.ReactionRequestDto;
import com.mulmeong.utility.application.port.out.dto.ReactionResponseDto;
import com.mulmeong.utility.domain.model.Reaction;
import org.springframework.stereotype.Component;

@Component
public class ReactionDtoMapper {

    public ReactionResponseDto toReactionDto(Reaction reaction) {
        return ReactionResponseDto.builder()
                .memberUuid(reaction.getMemberUuid())
                .kind(reaction.getKind())
                .kindUuid(reaction.getKindUuid())
                .status(reaction.getStatus())
                .build();
    }

}
