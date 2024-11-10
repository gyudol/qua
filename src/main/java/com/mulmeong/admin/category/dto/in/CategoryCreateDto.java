package com.mulmeong.admin.category.dto.in;

import com.mulmeong.admin.category.domain.Category;
import com.mulmeong.admin.category.domain.ViewType;
import com.mulmeong.admin.category.vo.in.CategoryCreateVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryCreateDto {

    private String categoryName;
    private ViewType viewType;

    public static CategoryCreateDto toDto(CategoryCreateVo requestVo) {
        return CategoryCreateDto.builder()
                .categoryName(requestVo.getCategoryName())
                .viewType(ViewType.fromString(requestVo.getViewType()))
                .build();
    }

    public Category toEntity() {
        return Category.builder()
                .categoryName(this.categoryName)
                .viewType(this.viewType)
                .build();
    }

    @Builder
    public CategoryCreateDto(String categoryName, ViewType viewType) {
        this.categoryName = categoryName;
        this.viewType = viewType;
    }
}
