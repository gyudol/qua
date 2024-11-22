package com.mulmeong.feed.api.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MediaInfo {

    private String mediaUrl;
    private MediaSubtype mediaSubtype;
    private String description;

    @Builder
    public MediaInfo(String mediaUrl, MediaSubtype mediaSubtype, String description) {
        this.mediaUrl = mediaUrl;
        this.mediaSubtype = mediaSubtype;
        this.description = description;
    }

}
