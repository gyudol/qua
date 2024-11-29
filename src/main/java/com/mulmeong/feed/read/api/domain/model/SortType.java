package com.mulmeong.feed.read.api.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {

    LATEST("최신순"),
    LIKES("좋아요순");

    private final String sortType;

}
