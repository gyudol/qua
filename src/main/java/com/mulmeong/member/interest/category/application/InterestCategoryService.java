package com.mulmeong.member.interest.category.application;

import com.mulmeong.member.interest.category.dto.in.CategoryCreateDto;
import com.mulmeong.member.interest.category.dto.in.CategoryUpdateDto;
import com.mulmeong.member.interest.category.dto.out.CategoryDto;

import java.util.List;

public interface InterestCategoryService {

    void createInterestCategory(CategoryCreateDto requestDto);

    List<CategoryDto> getInterestCategoryList(String memberUuid);

    void updateInterestCategory(CategoryUpdateDto requestDto);
}
