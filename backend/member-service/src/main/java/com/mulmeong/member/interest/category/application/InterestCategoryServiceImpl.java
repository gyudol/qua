package com.mulmeong.member.interest.category.application;

import com.mulmeong.member.interest.category.dto.in.CategoryCreateDto;
import com.mulmeong.member.interest.category.dto.in.CategoryUpdateDto;
import com.mulmeong.member.interest.category.dto.out.CategoryDto;
import com.mulmeong.member.interest.category.infrastructure.InterestCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InterestCategoryServiceImpl implements InterestCategoryService {

    private final InterestCategoryRepository interestCategoryRepository;

    /**
     * 회원의 관심 카테고리 생성.
     *
     * @param requestDto 회원 uuid + 카테고리명 리스트
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createInterestCategory(CategoryCreateDto requestDto) {

        interestCategoryRepository.saveAll(requestDto.toEntities());
    }

    /**
     * 회원의 관심 카테고리 전체 목록 조회.
     *
     * @param memberUuid 회원 uuid
     * @return 관심 카테고리 목록
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getInterestCategoryList(String memberUuid) {

        return interestCategoryRepository.findByMemberUuid(memberUuid).stream()
                .map(CategoryDto::fromEntity)
                .toList();
    }

    /**
     * 회원의 관심 카테고리 수정
     * 부분 수정이 아닌 전체 삭제 후 재삽입(데이터 양이 적고, 연산이 적기 때문).
     *
     * @param requestDto 회원 uuid + 카테고리Id 리스트
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInterestCategory(CategoryUpdateDto requestDto) {

        interestCategoryRepository.deleteAllByMemberUuid(requestDto.getMemberUuid());
        interestCategoryRepository.flush();

        interestCategoryRepository.saveAll(requestDto.toEntities());
    }
}
