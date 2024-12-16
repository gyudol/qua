package com.mulmeong.event.produce;


import com.mulmeong.utility.application.port.in.dto.DislikeRenewRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DislikeRenewCreateEvent {

    private String kind;
    private String kindUuid;
    private Long dislikeCount;

    public static DislikeRenewCreateEvent toDto(DislikeRenewRequestDto requestDto) {
        return DislikeRenewCreateEvent.builder()
                .kind(requestDto.getKind())
                .kindUuid(requestDto.getKindUuid())
                .dislikeCount(requestDto.getDislikeCount())
                .build();
    }


}
