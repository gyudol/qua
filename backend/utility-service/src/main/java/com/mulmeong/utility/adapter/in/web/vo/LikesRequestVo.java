package com.mulmeong.utility.adapter.in.web.vo;

import com.mulmeong.utility.application.port.in.dto.LikesRenewRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class LikesRequestVo {

    private String memberUuid;
    private String kind;
    private String kindUuid;
    private Long likeCount;

    public LikesRequestDto toDto() {
        return LikesRequestDto.builder()
                .memberUuid(memberUuid)
                .kind(kind)
                .kindUuid(kindUuid)
                .build();
    }

    public LikesRenewRequestDto toValid() {
        return LikesRenewRequestDto.builder()
                .kind(kind)
                .kindUuid(kindUuid)
                .likeCount(likeCount)
                .build();
    }
}
