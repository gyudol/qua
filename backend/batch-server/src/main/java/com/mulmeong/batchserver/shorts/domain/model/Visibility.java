package com.mulmeong.batchserver.shorts.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Visibility {

    VISIBLE("공개"),
    HIDDEN("비공개"),
    REPORTED("신고 처리됨");

    private final String visibility;

}
