package com.mulmeong.feed.api.vo.in;

import lombok.Getter;

@Getter
public class UpdateFeedRequestVo {

    private String title;
    private String content;
    private Long categoryId;

}
