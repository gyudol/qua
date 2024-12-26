package com.mulmeong.feed.read.api.dto.in;

import com.mulmeong.feed.read.api.vo.in.IndexSyncRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class IndexSyncRequestDto {

    private String lastId;
    private Integer limit;

    public static IndexSyncRequestDto toDto(IndexSyncRequestVo requestVo) {
        return IndexSyncRequestDto.builder()
            .offset(requestVo.getLastId())
            .limit(requestVo.getLimit())
            .build();
    }

    @Builder
    public IndexSyncRequestDto(String offset, Integer limit) {
        this.lastId = offset;
        this.limit = limit;
    }

}
