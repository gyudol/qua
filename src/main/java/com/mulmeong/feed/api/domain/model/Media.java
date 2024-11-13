package com.mulmeong.feed.api.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Media {

    private String mediaUrl;
    private MediaType mediaType;
    private String description;

    @Builder
    public Media(String mediaUrl, MediaType mediaType, String description) {
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.description = description;
    }

}
