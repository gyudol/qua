package com.mulmeong.shorts.read.api.domain.model;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
