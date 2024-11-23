package com.mulmeong.feed.api.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MediaInfo {

    @NotBlank(message = "mediaURL은 필수 입력 항목입니다.")
    private String mediaUrl;
    @NotNull(message = "mediaSubtype은 필수 입력 항목입니다.")
    private MediaSubtype mediaSubtype;
    private String description;

    @Builder
    public MediaInfo(String mediaUrl, MediaSubtype mediaSubtype, String description) {
        this.mediaUrl = mediaUrl;
        this.mediaSubtype = mediaSubtype;
        this.description = description;
    }

}
