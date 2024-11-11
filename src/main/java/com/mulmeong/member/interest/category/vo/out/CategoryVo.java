package com.mulmeong.member.interest.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryVo {

    private Long categoryId;
    private String categoryName;

    @Builder
    public CategoryVo(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
