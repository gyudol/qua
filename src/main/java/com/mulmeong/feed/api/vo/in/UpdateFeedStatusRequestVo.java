package com.mulmeong.feed.api.vo.in;

import com.mulmeong.feed.api.domain.model.Visibility;
import com.mulmeong.feed.api.dto.in.UpdateFeedRequestDto;
import lombok.Getter;

@Getter
public class UpdateFeedStatusRequestVo {

    private Visibility visibility;

}
