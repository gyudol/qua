package com.mulmeong.event.produce;


import com.mulmeong.utility.application.port.in.dto.DislikesRenewRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DislikesRenewEvent {

    private String kind;
    private String kindUuid;
    private Long dislikeCount;

    public static DislikesRenewEvent toDto(DislikesRenewRequestDto requestDto) {
        return DislikesRenewEvent.builder()
                .kind(requestDto.getKind())
                .kindUuid(requestDto.getKindUuid())
                .dislikeCount(requestDto.getDislikeCount())
                .build();
    }


}
