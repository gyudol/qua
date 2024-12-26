package com.mulmeong.shorts.read.api.dto.in;

import com.mulmeong.shorts.read.api.domain.model.SortType;
import com.mulmeong.shorts.read.api.dto.model.BasePaginationRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsRecommendationRequestDto extends BasePaginationRequestDto {

    private String memberUuid;
    private boolean isMember;

    public static ShortsRecommendationRequestDto toDto(String memberUuid, boolean isMember,
        String lastId, Integer pageSize, Integer pageNo) {

        return ShortsRecommendationRequestDto.builder()
            .memberUuid(memberUuid)
            .isMember(isMember)
            .lastId(lastId)
            .pageSize(pageSize)
            .pageNo(pageNo)
            .build();
    }

    @Builder
    public ShortsRecommendationRequestDto(SortType sortType, String lastId, Integer pageSize,
        Integer pageNo, String memberUuid, boolean isMember) {

        super(sortType, lastId, pageSize, pageNo);
        this.memberUuid = memberUuid;
        this.isMember = isMember;
    }

}
