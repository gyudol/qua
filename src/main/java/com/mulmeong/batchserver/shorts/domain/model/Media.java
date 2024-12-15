package com.mulmeong.batchserver.shorts.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
public class Media {

    private String mediaUuid;
    private Map<MediaType, MediaInfo> assets;

    @Builder
    public Media(String mediaUuid, Map<MediaType, MediaInfo> assets) {
        this.mediaUuid = mediaUuid;
        this.assets = assets;
    }

}
