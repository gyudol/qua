package com.mulmeong.admin.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryVo {
    private Long categoryId;
    private String categoryName;
    private String viewType;

    @Builder
    public CategoryVo(Long categoryId, String categoryName, String viewType) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.viewType = viewType;
    }
}
