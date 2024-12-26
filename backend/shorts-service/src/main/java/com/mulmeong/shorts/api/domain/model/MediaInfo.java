package com.mulmeong.shorts.api.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MediaInfo {

    @NotBlank(message = "mediaURL은 필수 입력 항목입니다.")
    private String mediaUrl;
    private String description;

    @Builder
    public MediaInfo(String mediaUrl, String description) {
        this.mediaUrl = mediaUrl;
        this.description = description;
    }

}
