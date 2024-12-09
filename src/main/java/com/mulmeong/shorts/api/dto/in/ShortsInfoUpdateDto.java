package com.mulmeong.shorts.api.dto.in;

import com.mulmeong.shorts.api.domain.entity.Shorts;
import com.mulmeong.shorts.api.domain.event.ShortsInfoUpdateEvent;
import com.mulmeong.shorts.api.vo.in.ShortsInfoUpdateVo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsInfoUpdateDto {

    private String shortsUuid;
    private String title;
    private Short playtime;
    private LocalDateTime updatedAt;

    public static ShortsInfoUpdateDto toDto(String shortsUuid, ShortsInfoUpdateVo requestVo) {
        return ShortsInfoUpdateDto.builder()
            .shortsUuid(shortsUuid)
            .title(requestVo.getTitle())
            .playtime(requestVo.getPlaytime())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public Shorts toShortsEntity(Shorts existingShorts) {
        return Shorts.builder()
            .id(existingShorts.getId())
            .shortsUuid(shortsUuid)
            .memberUuid(existingShorts.getMemberUuid())
            .title(title)
            .playtime(playtime)
            .visibility(existingShorts.getVisibility())
            .updatedAt(updatedAt)
            .build();
    }

    public ShortsInfoUpdateEvent toEventEntity() {  // to Kafka EventEntity
        return ShortsInfoUpdateEvent.builder()
            .shortsUuid(shortsUuid)
            .title(title)
            .playtime(playtime)
            .updatedAt(updatedAt)
            .build();
    }

    @Builder
    public ShortsInfoUpdateDto(String shortsUuid, String title, Short playtime,
        LocalDateTime updatedAt) {

        this.shortsUuid = shortsUuid;
        this.title = title;
        this.playtime = playtime;
        this.updatedAt = updatedAt;
    }
}
