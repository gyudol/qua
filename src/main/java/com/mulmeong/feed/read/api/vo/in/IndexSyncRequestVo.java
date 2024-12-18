package com.mulmeong.feed.read.api.vo.in;

import lombok.Getter;

@Getter
public class IndexSyncRequestVo {

    private String lastId;
    private Integer limit;

}
