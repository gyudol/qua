package com.mulmeong.contest.dto.model;


import com.mulmeong.contest.domain.model.SortType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BasePaginationRequestDto {

    private SortType sortType;
    private String lastId;
    private Integer pageSize;
    private Integer pageNo;

}
