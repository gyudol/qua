package com.mulmeong.utility.adapter.in.web.vo;

import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;
import com.mulmeong.utility.application.port.in.dto.DislikesRenewRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRenewRequestDto;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class DislikeRequestVo {

    private String memberUuid;
    private String kind;
    private String kindUuid;
    private Long dislikeCount;

    public DislikeRequestDto toDto() {
        return DislikeRequestDto.builder()
                .memberUuid(memberUuid)
                .kind(kind)
                .kindUuid(kindUuid)
                .build();
    }

    public DislikesRenewRequestDto toValid() {
        return DislikesRenewRequestDto.builder()
                .kind(kind)
                .kindUuid(kindUuid)
                .dislikeCount(dislikeCount)
                .build();
    }
}
