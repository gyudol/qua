package com.mulmeong.contest.read.dto.in;

import com.mulmeong.contest.read.dto.model.BasePaginationRequestDto;
import com.mulmeong.contest.read.domain.model.SortType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ContestPostRequestDto extends BasePaginationRequestDto {

    private Long contestId;

    @Builder
    public ContestPostRequestDto(
            Long contestId,
            SortType sortType,
            String lastId,
            Integer pageSize,
            Integer pageNo
    ) {
        super(sortType, lastId, pageSize, pageNo);
        this.contestId = contestId;
    }

    public static ContestPostRequestDto toDto(
            Long contestId,
            String sortType,
            String lastId,
            Integer pageSize,
            Integer pageNo
    ) {
        return ContestPostRequestDto.builder()
                .contestId(contestId)
                .sortType(SortType.valueOf(sortType.toUpperCase()))
                .lastId(lastId)
                .pageSize(pageSize)
                .pageNo(pageNo)
                .build();
    }
}
