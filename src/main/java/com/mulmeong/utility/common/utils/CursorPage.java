package com.mulmeong.utility.common.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CursorPage<T> {

    private List<T> content;
    private String nextCursor;
    private Boolean hasNext;
    private Integer pageSize;

    public static <T, U> CursorPage<T> toCursorPage(CursorPage<U> cursorPage, List<T> content) {

        return CursorPage.<T>builder()
                .content(content)
                .nextCursor(cursorPage.getNextCursor())
                .hasNext(cursorPage.getHasNext())
                .pageSize(cursorPage.getPageSize())
                .build();
    }

}