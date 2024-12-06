package com.mulmeong.contest.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediaType {

    IMAGE("사진"),
    VIDEO("영상");

    private final String mediaType;
}
