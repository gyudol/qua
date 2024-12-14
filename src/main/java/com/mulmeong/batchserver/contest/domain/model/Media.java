package com.mulmeong.batchserver.contest.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class Media {

    private String mediaUuid;
    private MediaType mediaType;
    private Map<@Valid MediaSubtype, @Valid MediaInfo> assets;

    @Builder
    public Media(String mediaUuid, MediaType mediaType, Map<MediaSubtype, MediaInfo> assets) {
        this.mediaUuid = mediaUuid;
        this.mediaType = mediaType;
        this.assets = assets;
    }

}
