package com.mulmeong.shorts.read.api.dto.in;

import com.mulmeong.shorts.read.api.domain.model.SortType;
import com.mulmeong.shorts.read.api.dto.model.BasePaginationRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsAuthorRequestDto extends BasePaginationRequestDto {

    private String authorUuid;
    private String requesterUuid;

    public static ShortsAuthorRequestDto toDto(String authorUuid, String requesterUuid,
        String sortBy, String lastId, Integer pageSize, Integer pageNo) {

        return ShortsAuthorRequestDto.builder()
            .authorUuid(authorUuid)
            .requesterUuid(requesterUuid)
            .sortType(SortType.valueOf(sortBy.toUpperCase()))
            .lastId(lastId)
            .pageSize(pageSize)
            .pageNo(pageNo)
            .build();
    }

    @Builder
    public ShortsAuthorRequestDto(SortType sortType, String lastId, Integer pageSize,
        Integer pageNo, String authorUuid, String requesterUuid) {

        super(sortType, lastId, pageSize, pageNo);
        this.authorUuid = authorUuid;
        this.requesterUuid = requesterUuid;
    }
}
