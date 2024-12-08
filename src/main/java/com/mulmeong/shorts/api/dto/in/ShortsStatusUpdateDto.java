package com.mulmeong.shorts.api.dto.in;

import com.mulmeong.shorts.api.domain.entity.Shorts;
import com.mulmeong.shorts.api.domain.event.ShortsStatusUpdateEvent;
import com.mulmeong.shorts.api.domain.model.Visibility;
import com.mulmeong.shorts.api.vo.in.ShortsStatusUpdateVo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsStatusUpdateDto {

    private String shortsUuid;
    private Visibility visibility;
    private LocalDateTime updatedAt;

    public static ShortsStatusUpdateDto toDto(String shortsUuid, ShortsStatusUpdateVo requestVo) {
        return ShortsStatusUpdateDto.builder()
            .shortsUuid(shortsUuid)
            .visibility(requestVo.getVisibility())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public Shorts toShortsEntity(Shorts existingShorts) {   // update visibility
        return Shorts.builder()
            .id(existingShorts.getId())
            .shortsUuid(shortsUuid)
            .memberUuid(existingShorts.getMemberUuid())
            .title(existingShorts.getTitle())
            .playtime(existingShorts.getPlaytime())
            .visibility(visibility)
            .updatedAt(updatedAt)
            .build();
    }

    public ShortsStatusUpdateEvent toEventEntity() {
        return ShortsStatusUpdateEvent.builder()
            .shortsUuid(shortsUuid)
            .visibility(visibility)
            .updatedAt(updatedAt)
            .build();
    }

    @Builder
    public ShortsStatusUpdateDto(String shortsUuid, Visibility visibility,
        LocalDateTime updatedAt) {

        this.shortsUuid = shortsUuid;
        this.visibility = visibility;
        this.updatedAt = updatedAt;
    }

}
