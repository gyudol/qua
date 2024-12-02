package com.mulmeong.contest.read.dto.model;

import com.mulmeong.contest.read.entity.SortType;
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
