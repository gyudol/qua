package com.mulmeong.admin.category.application;

import com.mulmeong.admin.category.domain.Category;
import com.mulmeong.admin.category.dto.in.CategoryCreateDto;
import com.mulmeong.admin.category.dto.in.CategoryUpdateDto;
import com.mulmeong.admin.category.dto.out.CategoryDto;
import com.mulmeong.admin.category.infrastructure.CategoryRepository;
import com.mulmeong.admin.common.exception.BaseException;
import com.mulmeong.admin.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 생성.
     *
     * @param requestDto 카테고리 생성 요청 DTO
     */
    @Override
    public void createCategory(CategoryCreateDto requestDto) {
        if (categoryRepository.existsByCategoryName(requestDto.getCategoryName())) {
            throw new BaseException(BaseResponseStatus.EXIST_CATEGORY);
        }
        categoryRepository.save(requestDto.toEntity());
    }

    /**
     * 카테고리 전체 조회(카테고리 수가 많지 않음).
     *
     * @return 카테고리 리스트
     */
    @Override
    public List<CategoryDto> getCategoryList() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .toList();
    }

    /**
     * 카테고리 정보 수정(name or viewType).
     *
     * @param requestDto 카테고리 수정 요청 DTO
     */
    @Override
    public void updateCategory(CategoryUpdateDto requestDto) {
        Category category = categoryRepository.findById(requestDto.getId()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));

        categoryRepository.save(requestDto.toEntity(category));
    }

    /**
     * 카테고리 삭제.
     *
     * @param categoryId 카테고리 id
     */
    @Override
    public void deleteCategory(Long categoryId) {
        // TODO : 카테고리 삭제를 둘 경우, 해당 카테고리에 속한 피드들은 어떻게 처리할 지
        // FIXME : 또한 카테고리의 경우 ID만 보내면 삭제할 수 있음 => 논의 필요
        categoryRepository.delete(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)));
    }
}
