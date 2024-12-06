package com.mulmeong.event.produce;


import com.mulmeong.utility.application.port.in.dto.LikesRenewRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikesRenewEvent {

    private String kind;
    private String kindUuid;
    private Long likeCount;

    public static LikesRenewEvent toDto(LikesRenewRequestDto requestDto) {
        return LikesRenewEvent.builder()
                .kind(requestDto.getKind())
                .kindUuid(requestDto.getKindUuid())
                .likeCount(requestDto.getLikeCount())
                .build();
    }


}
