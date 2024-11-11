package com.mulmeong.member.interest.category.presentation;

import com.mulmeong.member.common.response.BaseResponse;
import com.mulmeong.member.interest.category.application.InterestCategoryService;
import com.mulmeong.member.interest.category.dto.in.CategoryCreateDto;
import com.mulmeong.member.interest.category.dto.in.CategoryUpdateDto;
import com.mulmeong.member.interest.category.dto.out.CategoryDto;
import com.mulmeong.member.interest.category.vo.in.CategoryCreateVo;
import com.mulmeong.member.interest.category.vo.in.CategoryUpdateVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "관심 카테고리", description = "관심 카테고리 API")
@RequiredArgsConstructor
@RequestMapping("v1/members")
@RestController
public class InterestCategoryController {

    private final InterestCategoryService interestCategoryService;

    @Operation(summary = "관심 카테고리 생성", description = "관심 카테고리 생성")
    @PostMapping("/{memberUuid}/interests/categories")
    public BaseResponse<Void> createInterestCategory(@PathVariable String memberUuid,
                                                     @RequestBody CategoryCreateVo requestVo) {
        interestCategoryService.createInterestCategory(CategoryCreateDto.toDto(memberUuid, requestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "관심 카테고리 조회", description = "관심 카테고리 조회")
    @GetMapping("/{memberUuid}/interests/categories")
    public BaseResponse<List<CategoryDto>> getInterestCategory(@PathVariable String memberUuid) {

        return new BaseResponse<>(interestCategoryService.getInterestCategoryList(memberUuid));
    }

    @Operation(summary = "관심 카테고리 수정", description = "관심 카테고리 수정")
    @PutMapping("/{memberUuid}/interests/categories")
    public BaseResponse<Void> updateInterestCategory(@PathVariable String memberUuid,
                                                     @RequestBody CategoryUpdateVo requestVo) {
        interestCategoryService.updateInterestCategory(CategoryUpdateDto.toDto(memberUuid, requestVo));
        return new BaseResponse<>();
    }


}
