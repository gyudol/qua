package com.mulmeong.feed.read.api.dto.in;

import com.mulmeong.feed.read.api.domain.model.SortType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedFilterRequestDto {

    private String categoryName;
    private String hashtagName;
    private SortType sortType;
    private String lastId;
    private Integer pageSize;
    private Integer pageNo;

    public static FeedFilterRequestDto toDto(String categoryName, String hashtagName, String sortBy,
        String lastId, Integer pageSize, Integer pageNo) {

        return FeedFilterRequestDto.builder()
            .categoryName(categoryName)
            .hashtagName(hashtagName)
            .sortType(SortType.valueOf(sortBy.toUpperCase()))
            .lastId(lastId)
            .pageSize(pageSize)
            .pageNo(pageNo)
            .build();
    }

    @Builder
    public FeedFilterRequestDto(String hashtagName, SortType sortType, String categoryName,
        String lastId, Integer pageSize, Integer pageNo) {

        this.hashtagName = hashtagName;
        this.sortType = sortType;
        this.categoryName = categoryName;
        this.lastId = lastId;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

}
