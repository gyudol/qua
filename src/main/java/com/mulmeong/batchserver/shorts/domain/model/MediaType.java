package com.mulmeong.batchserver.shorts.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediaType {

    VIDEO_THUMBNAIL("비디오 썸네일"),
    STREAMING_360("360p 스트리밍"),
    STREAMING_540("540p 스트리밍"),
    STREAMING_720("HD 스트리밍"),
    VIDEO_MP4("비디오 압축");

    private final String mediaType;

}
