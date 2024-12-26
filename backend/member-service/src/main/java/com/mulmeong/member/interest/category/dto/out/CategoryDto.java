package com.mulmeong.member.interest.category.dto.out;

import com.mulmeong.member.interest.category.domain.InterestCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CategoryDto {

    private Long id;

    public static CategoryDto fromEntity(InterestCategory interestCategory) {
        return CategoryDto.builder()
                .id(interestCategory.getCategoryId())
                .build();
    }
}
