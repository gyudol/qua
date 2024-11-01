package com.mulmeong.utility.application.port.out.dto;

import com.mulmeong.utility.application.port.in.dto.ReactionRequestDto;
import com.mulmeong.utility.domain.model.Reaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionResponseDto {

    private String memberUuid;
    private String kind;
    private String kindUuid;
    private Integer status;

    public Reaction toEntity() {
        return Reaction.builder()
                .memberUuid(memberUuid)
                .kind(kind)
                .kindUuid(kindUuid)
                .status(status)
                .build();
    }


}
