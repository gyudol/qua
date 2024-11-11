package com.mulmeong.utility.adapter.in.web.vo;

import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class DislikeRequestVo {

    private String memberUuid;
    private String kind;
    private String kindUuid;

    public DislikeRequestDto toDto() {
        return DislikeRequestDto.builder()
                .memberUuid(memberUuid)
                .kind(kind)
                .kindUuid(kindUuid)
                .build();
    }
}
