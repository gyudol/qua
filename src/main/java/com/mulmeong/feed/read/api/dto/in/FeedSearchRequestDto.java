package com.mulmeong.feed.read.api.dto.in;

import com.mulmeong.feed.read.api.domain.model.SortType;
import com.mulmeong.feed.read.api.dto.model.BasePaginationRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedSearchRequestDto extends BasePaginationRequestDto {

    private String keyword;

    public static FeedSearchRequestDto toDto(String keyword, Integer pageSize, Integer pageNo) {

        return FeedSearchRequestDto.builder()
            .keyword(keyword)
            .pageSize(pageSize)
            .pageNo(pageNo)
            .build();
    }

    @Builder
    public FeedSearchRequestDto(SortType sortType, String lastId, Integer pageSize, Integer pageNo,
        String keyword) {

        super(sortType, lastId, pageSize, pageNo);
        this.keyword = keyword;
    }
}
