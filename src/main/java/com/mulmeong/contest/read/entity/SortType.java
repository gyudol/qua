package com.mulmeong.contest.read.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {

    LATEST("최신순"),
    VOTES("투표순");

    private final String sortType;

}
