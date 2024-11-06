package com.mulmeong.comment.common.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CursorPage<T> {

    private List<T> list;
    private Long nextCursor;
    private Boolean hasNext;
    private Integer pageSize;
    private Integer page;

    public boolean hasNext() {
        return nextCursor != null;
    }

    @Builder
    public CursorPage(List<T> list, Long nextCursor, Boolean hasNext, Integer pageSize, Integer page) {
        this.list = list;
        this.nextCursor = nextCursor;
        this.hasNext = hasNext;
        this.pageSize = pageSize;
        this.page = page;
    }
}
