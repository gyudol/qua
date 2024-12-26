package com.mulmeong.contest.read.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediaType {

    IMAGE("이미지"),
    VIDEO("비디오");

    private final String mediaType;
}
