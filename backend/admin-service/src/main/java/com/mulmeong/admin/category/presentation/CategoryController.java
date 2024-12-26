package com.mulmeong.admin.category.presentation;

import com.mulmeong.admin.category.application.CategoryService;
import com.mulmeong.admin.category.dto.out.CategoryDto;
import com.mulmeong.admin.category.vo.out.CategoryVo;
import com.mulmeong.admin.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "카테고리", description = "카테고리 CRUD API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "전체 카테고리 목록 조회", description = "전체 카테고리 목록 조회")
    @GetMapping
    public BaseResponse<List<CategoryVo>> getCategories() {

        return new BaseResponse<>(categoryService.getCategoryList().stream()
                .map(CategoryDto::toVo)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "카테고리 단건 조회", description = "Id로 카테고리 조회")
    @GetMapping("/{categoryId}")
    public BaseResponse<CategoryVo> getCategory(@PathVariable Long categoryId) {

        return new BaseResponse<>(categoryService.getCategory(categoryId).toVo());
    }


}
