package com.mulmeong.admin.category.application;

import com.mulmeong.admin.category.dto.in.CategoryCreateDto;
import com.mulmeong.admin.category.dto.in.CategoryUpdateDto;
import com.mulmeong.admin.category.dto.out.CategoryDto;

import java.util.List;

public interface CategoryService {

    public void createCategory(CategoryCreateDto requestDto);

    public void updateCategory(CategoryUpdateDto requestDto);

    public void deleteCategory(Long categoryId);

    public CategoryDto getCategory(Long categoryId);

    public List<CategoryDto> getCategoryList();

}
