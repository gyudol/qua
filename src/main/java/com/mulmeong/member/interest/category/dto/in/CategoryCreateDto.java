package com.mulmeong.member.interest.category.dto.in;

import com.mulmeong.member.interest.category.domain.InterestCategory;
import com.mulmeong.member.interest.category.vo.in.Category;
import com.mulmeong.member.interest.category.vo.in.CategoryCreateVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateDto {

    private String memberUuid;
    private List<Long> categoryIds;

    public static CategoryCreateDto toDto(String memberUuid, CategoryCreateVo requestVo) {
        return CategoryCreateDto.builder()
                .memberUuid(memberUuid)
                // VO -> DTO : List<Category> -> List<Long>
                .categoryIds(requestVo.getCategories().stream().map(Category::getId).toList())
                .build();
    }

    public List<InterestCategory> toEntities() {
        return categoryIds.stream()
                .map(categoryId -> InterestCategory.builder()
                        .memberUuid(memberUuid)
                        .categoryId(categoryId)
                        .build())
                .toList();
    }
}
