package com.mulmeong.contest.dto.in;

import com.mulmeong.contest.domain.model.SortType;
import com.mulmeong.contest.dto.model.BasePaginationRequestDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ContestQueryRequestDto extends BasePaginationRequestDto {

    private boolean status;


    @Builder
    public ContestQueryRequestDto(
            boolean status,
            SortType sortType,
            String lastId,
            Integer pageSize,
            Integer pageNo
    ) {
        super(sortType, lastId, pageSize, pageNo);
        this.status = status;
    }

    public static ContestQueryRequestDto toDto(
            boolean status,
            String sortType,
            String lastId,
            Integer pageSize,
            Integer pageNo
    ) {
        return ContestQueryRequestDto.builder()
                .status(status)
                .sortType(SortType.valueOf(sortType.toUpperCase()))
                .lastId(lastId)
                .pageSize(pageSize)
                .pageNo(pageNo)
                .build();
    }
}
