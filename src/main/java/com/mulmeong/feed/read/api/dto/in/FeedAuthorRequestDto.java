package com.mulmeong.feed.read.api.dto.in;

import com.mulmeong.feed.read.api.domain.model.SortType;
import com.mulmeong.feed.read.api.dto.model.BasePaginationRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedAuthorRequestDto extends BasePaginationRequestDto {

    private String authorUuid;
    private String requesterUuid;

    public static FeedAuthorRequestDto toDto(String authorUuid, String requesterUuid, String sortBy,
        String lastId, Integer pageSize, Integer pageNo) {

        return FeedAuthorRequestDto.builder()
            .authorUuid(authorUuid)
            .requesterUuid(requesterUuid)
            .sortType(SortType.valueOf(sortBy.toUpperCase()))
            .lastId(lastId)
            .pageSize(pageSize)
            .pageNo(pageNo)
            .build();
    }

    @Builder
    public FeedAuthorRequestDto(String authorUuid, String requesterUuid, SortType sortType,
        String lastId, Integer pageSize, Integer pageNo) {

        super(sortType, lastId, pageSize, pageNo);
        this.authorUuid = authorUuid;
        this.requesterUuid = requesterUuid;
    }

}
