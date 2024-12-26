package com.mulmeong.shorts.api.dto.in;

import com.mulmeong.shorts.api.domain.document.ShortsHashtag;
import com.mulmeong.shorts.api.domain.entity.Shorts;
import com.mulmeong.shorts.api.domain.event.ShortsHashtagUpdateEvent;
import com.mulmeong.shorts.api.domain.model.Hashtag;
import com.mulmeong.shorts.api.vo.in.ShortsHashtagUpdateVo;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsHashtagUpdateDto {

    private String shortsUuid;
    private List<Hashtag> hashtags;
    private LocalDateTime updatedAt;

    public static ShortsHashtagUpdateDto toDto(String shortsUuid, ShortsHashtagUpdateVo requestVo) {
        return ShortsHashtagUpdateDto.builder()
            .shortsUuid(shortsUuid)
            .hashtags(requestVo.getHashtags())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public ShortsHashtag toShortsHashtagEntity() {
        if (hashtags == null) {
            hashtags = List.of();
        }

        return ShortsHashtag.builder()
            .shortsUuid(shortsUuid)
            .hashtags(hashtags)
            .build();
    }

    public Shorts toShortsEntity(Shorts existingShorts) {
        return Shorts.builder()
            .id(existingShorts.getId())
            .shortsUuid(shortsUuid)
            .memberUuid(existingShorts.getMemberUuid())
            .title(existingShorts.getTitle())
            .playtime(existingShorts.getPlaytime())
            .visibility(existingShorts.getVisibility())
            .updatedAt(updatedAt)
            .build();
    }

    public ShortsHashtagUpdateEvent toEventEntity() {
        return ShortsHashtagUpdateEvent.builder()
            .shortsUuid(shortsUuid)
            .hashtags(hashtags)
            .updatedAt(updatedAt)
            .build();
    }

    @Builder
    public ShortsHashtagUpdateDto(String shortsUuid, List<Hashtag> hashtags,
        LocalDateTime updatedAt) {

        this.shortsUuid = shortsUuid;
        this.hashtags = hashtags;
        this.updatedAt = updatedAt;
    }

}
