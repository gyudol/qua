package com.mulmeong.shorts.api.dto.in;

import com.mulmeong.shorts.api.domain.document.ShortsHashtag;
import com.mulmeong.shorts.api.domain.document.ShortsMedia;
import com.mulmeong.shorts.api.domain.entity.Shorts;
import com.mulmeong.shorts.api.domain.event.ShortsCreateEvent;
import com.mulmeong.shorts.api.domain.model.Hashtag;
import com.mulmeong.shorts.api.domain.model.Media;
import com.mulmeong.shorts.api.domain.model.Visibility;
import com.mulmeong.shorts.api.vo.in.ShortsCreateVo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsCreateDto {

    private String shortsUuid;
    private String memberUuid;
    private String title;
    private Short playtime;
    private Visibility visibility;
    private List<Hashtag> hashtags;
    private Media media;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ShortsCreateDto toDto(ShortsCreateVo requestVo) {
        LocalDateTime now = LocalDateTime.now();

        return ShortsCreateDto.builder()
            .shortsUuid(UUID.randomUUID().toString())
            .memberUuid(requestVo.getMemberUuid())
            .title(requestVo.getTitle())
            .playtime(requestVo.getPlaytime())
            .visibility(requestVo.getVisibility())
            .hashtags(requestVo.getHashtags())
            .media(requestVo.getMedia())
            .createdAt(now)
            .updatedAt(now)
            .build();
    }

    public Shorts toShortsEntity() {
        return Shorts.builder()
            .shortsUuid(shortsUuid)
            .memberUuid(memberUuid)
            .title(title)
            .playtime(playtime)
            .visibility(visibility)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }

    public ShortsMedia toShortsMediaEntity() {
        return ShortsMedia.builder()
            .mediaUuid(media.getMediaUuid())
            .shortsUuid(shortsUuid)
            .assets(media.getAssets())
            .build();
    }

    public ShortsHashtag toShortsHashtagEntity() {
        if(hashtags == null) {
            hashtags = List.of();
        }

        return ShortsHashtag.builder()
            .shortsUuid(shortsUuid)
            .hashtags(hashtags)
            .build();
    }

    public ShortsCreateEvent toEventEntity() {  // to Kafka EventEntity
        return ShortsCreateEvent.builder()
            .shortsUuid(shortsUuid)
            .memberUuid(memberUuid)
            .title(title)
            .playtime(playtime)
            .visibility(visibility)
            .hashtags(hashtags)
            .media(media)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }

    @Builder
    public ShortsCreateDto(String shortsUuid, String memberUuid, String title, Short playtime,
        Visibility visibility, List<Hashtag> hashtags, Media media, LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.shortsUuid = shortsUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.playtime = playtime;
        this.visibility = visibility;
        this.hashtags = hashtags;
        this.media = media;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
