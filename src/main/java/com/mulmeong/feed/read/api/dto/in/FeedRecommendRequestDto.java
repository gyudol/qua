package com.mulmeong.feed.read.api.dto.in;

import com.mulmeong.feed.read.api.domain.model.SortType;
import com.mulmeong.feed.read.api.dto.model.BasePaginationRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedRecommendRequestDto extends BasePaginationRequestDto {

    private String memberUuid;

    public static FeedRecommendRequestDto toDto(String memberUuid, Integer pageSize,
        Integer pageNo) {

        return FeedRecommendRequestDto.builder()
            .memberUuid(memberUuid)
            .pageSize(pageSize)
            .pageNo(pageNo)
            .build();
    }

    @Builder
    public FeedRecommendRequestDto(String memberUuid, SortType sortType, String lastId,
        Integer pageSize, Integer pageNo) {

        super(sortType, lastId, pageSize, pageNo);
        this.memberUuid = memberUuid;
    }
}
