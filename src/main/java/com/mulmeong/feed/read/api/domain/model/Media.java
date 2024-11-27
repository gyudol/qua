package com.mulmeong.feed.read.api.domain.model;

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
    private MediaType mediaType;
    private Map<MediaSubtype, MediaInfo> assets;

    @Builder
    public Media(String mediaUuid, MediaType mediaType, Map<MediaSubtype, MediaInfo> assets) {
        this.mediaUuid = mediaUuid;
        this.mediaType = mediaType;
        this.assets = assets;
    }

}
