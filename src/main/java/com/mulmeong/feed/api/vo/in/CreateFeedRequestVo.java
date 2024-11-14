package com.mulmeong.feed.api.vo.in;

import com.mulmeong.feed.api.domain.model.Hashtag;
import com.mulmeong.feed.api.domain.model.Media;
import java.util.List;
import lombok.Getter;

@Getter
public class CreateFeedRequestVo {

    private String memberUuid;
    private String title;
    private String content;
    private Long categoryId;
    private List<Hashtag> hashtags;
    private List<Media> mediaList;

}
