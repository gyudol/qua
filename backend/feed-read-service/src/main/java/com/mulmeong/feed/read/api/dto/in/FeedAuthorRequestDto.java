package com.mulmeong.feed.read.api.dto.in;

import com.mulmeong.feed.read.api.domain.model.SortType;
import com.mulmeong.feed.read.api.dto.model.BasePaginationRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedAuthorRequestDto extends BasePaginationRequestDto {

    private String authorUuid;
    private boolean isOwner;

    public static FeedAuthorRequestDto toDto(String authorUuid, boolean isOwner, String sortBy,
        String lastId, Integer pageSize, Integer pageNo) {

        return FeedAuthorRequestDto.builder()
            .authorUuid(authorUuid)
            .isOwner(isOwner)
            .sortType(SortType.valueOf(sortBy.toUpperCase()))
            .lastId(lastId)
            .pageSize(pageSize)
            .pageNo(pageNo)
            .build();
    }

    @Builder
    public FeedAuthorRequestDto(String authorUuid, boolean isOwner, SortType sortType,
        String lastId, Integer pageSize, Integer pageNo) {

        super(sortType, lastId, pageSize, pageNo);
        this.authorUuid = authorUuid;
        this.isOwner = isOwner;
    }

}
