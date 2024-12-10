package com.mulmeong.contest.read.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MediaInfo {

    private String mediaUrl;
    private String description;

    @Builder
    public MediaInfo(String mediaUrl, String description) {
        this.mediaUrl = mediaUrl;
        this.description = description;
    }

}
