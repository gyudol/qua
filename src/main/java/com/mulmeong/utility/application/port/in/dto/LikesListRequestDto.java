package com.mulmeong.utility.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikesListRequestDto {

    private String memberUuid;
    private String kind;

    @Builder
    public LikesListRequestDto(
            String memberUuid,
            String kind
    ) {
        this.memberUuid = memberUuid;
        this.kind = kind;
    }
}
