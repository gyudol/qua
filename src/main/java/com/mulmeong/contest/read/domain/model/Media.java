package com.mulmeong.contest.read.domain.model;

import jakarta.validation.Valid;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class Media {

    private String mediaUuid;
    private MediaType mediaType;
    private Map<MediaSubtype, MediaInfo> assets;

    @Builder
    public Media(String mediaUuid, MediaType mediaType, Map<MediaSubtype, MediaInfo> assets) {
        this.mediaUuid = mediaUuid;
        this.mediaType = mediaType;
        this.assets = assets;
    }

}
