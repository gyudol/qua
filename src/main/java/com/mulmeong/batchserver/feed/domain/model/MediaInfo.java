package com.mulmeong.batchserver.feed.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MediaInfo {

    private String mediaUrl;
    private String description;

    @Builder
    public MediaInfo(String mediaUrl, String description) {
        this.mediaUrl = mediaUrl;
        this.description = description;
    }

}
