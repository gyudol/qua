package com.mulmeong.admin.category.presentation;

import com.mulmeong.admin.category.application.CategoryService;
import com.mulmeong.admin.category.dto.in.CategoryCreateDto;
import com.mulmeong.admin.category.dto.in.CategoryUpdateDto;
import com.mulmeong.admin.category.vo.in.CategoryCreateVo;
import com.mulmeong.admin.category.vo.in.CategoryUpdateVo;
import com.mulmeong.admin.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "카테고리", description = "카테고리 CRUD API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/v1/category")
public class CategoryAuthController {

    private final CategoryService categoryService;

    // todo: Admin 권한 체크(Admin 확대가 가시화 될 경우 추가)
    @Operation(summary = "카테고리 생성", description = "카테고리 생성")
    @PostMapping
    public BaseResponse<Void> createCategory(@Valid @RequestBody CategoryCreateVo requestVo) {
        // 중복 검사는 DataIntegrityViolationException를 GlobalExceptionHandler에서 처리
        categoryService.createCategory(CategoryCreateDto.toDto(requestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "카테고리 수정", description = "카테고리 정보 수정")
    @PutMapping("/{categoryId}")
    public BaseResponse<Void> updateCategory(@PathVariable Long categoryId,
                                             @Valid @RequestBody CategoryUpdateVo requestVo) {

        categoryService.updateCategory(CategoryUpdateDto.toDto(categoryId, requestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "카테고리 삭제", description = "Id로 카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public BaseResponse<Void> deleteCategory(@PathVariable Long categoryId) {

        categoryService.deleteCategory(categoryId);
        return new BaseResponse<>();
    }
}
