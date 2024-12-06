package com.mulmeong.event.produce;


import com.mulmeong.utility.application.port.in.dto.DislikesRenewRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DislikesCreateEvent {

    private String kind;
    private String kindUuid;
    private Long dislikeCount;

    public static DislikesCreateEvent toDto(DislikesRenewRequestDto requestDto) {
        return DislikesCreateEvent.builder()
                .kind(requestDto.getKind())
                .kindUuid(requestDto.getKindUuid())
                .dislikeCount(requestDto.getDislikeCount())
                .build();
    }


}
