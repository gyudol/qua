package com.mulmeong.feed.read.api.dto.in;

import com.mulmeong.feed.read.api.domain.model.SortType;
import com.mulmeong.feed.read.api.dto.model.BasePaginationRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedFilterRequestDto extends BasePaginationRequestDto {

    private String categoryName;
    private String hashtagName;

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

        super(sortType, lastId, pageSize, pageNo);
        this.hashtagName = hashtagName;
        this.categoryName = categoryName;
    }

}
