package com.mulmeong.utility.adapter.in.web.vo;

import com.mulmeong.utility.application.port.in.dto.ReactionRequestDto;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ReactionRequestVo {

    private String memberUuid;
    private String kind;
    private String kindUuid;

    public ReactionRequestDto toDto() {
        return ReactionRequestDto.builder()
                .memberUuid(memberUuid)
                .kind(kind)
                .kindUuid(kindUuid)
                .build();
    }
}
