package com.mulmeong.feed.api.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Media {

    private String mediaUuid;
    private MediaType mediaType;
    private List<MediaInfo> assets;

    @Builder
    public Media(String mediaUuid, MediaType mediaType, List<MediaInfo> assets) {
        this.mediaUuid = mediaUuid;
        this.mediaType = mediaType;
        this.assets = assets;
    }

}
