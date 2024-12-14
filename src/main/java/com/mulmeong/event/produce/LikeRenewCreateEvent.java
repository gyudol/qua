package com.mulmeong.event.produce;


import com.mulmeong.utility.application.port.in.dto.LikesRenewRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeRenewCreateEvent {

    private String kind;
    private String kindUuid;
    private Long likeCount;

    public static LikeRenewCreateEvent toDto(LikesRenewRequestDto requestDto) {
        return LikeRenewCreateEvent.builder()
                .kind(requestDto.getKind())
                .kindUuid(requestDto.getKindUuid())
                .likeCount(requestDto.getLikeCount())
                .build();
    }


}
