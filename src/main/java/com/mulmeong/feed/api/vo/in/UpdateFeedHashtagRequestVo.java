package com.mulmeong.feed.api.vo.in;

import com.mulmeong.feed.api.domain.model.Hashtag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;

@Getter
public class UpdateFeedHashtagRequestVo {

    private List<@Valid Hashtag> hashtags;

}
