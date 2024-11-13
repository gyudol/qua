package com.mulmeong.admin.category.dto.in;

import com.mulmeong.admin.category.domain.Category;
import com.mulmeong.admin.category.domain.ViewType;
import com.mulmeong.admin.category.vo.in.CategoryUpdateVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryUpdateDto {

    private Long id;
    private String categoryName;
    private ViewType viewType;

    public static CategoryUpdateDto toDto(Long categoryId, CategoryUpdateVo requestVo) {
        return CategoryUpdateDto.builder()
                .id(categoryId)
                .categoryName(requestVo.getCategoryName())
                .viewType(ViewType.fromString(requestVo.getViewType()))
                .build();
    }

    public Category toEntity(Category category) {
        return Category.builder()
                .id(category.getId())
                .categoryName(categoryName)
                .viewType(viewType)
                .build();
    }
}
