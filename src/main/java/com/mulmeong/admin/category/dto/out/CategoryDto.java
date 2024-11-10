package com.mulmeong.admin.category.dto.out;

import com.mulmeong.admin.category.domain.Category;
import com.mulmeong.admin.category.vo.out.CategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private String viewType;

    public static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getId())
                .categoryName(category.getCategoryName())
                .viewType(category.getViewType().toString())
                .build();

    }

    public CategoryVo toVo() {
        return CategoryVo.builder()
                .categoryId(this.categoryId)
                .categoryName(this.categoryName)
                .viewType(this.viewType)
                .build();
    }

    @Builder
    public CategoryDto(Long categoryId, String categoryName, String viewType) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.viewType = viewType;
    }
}
