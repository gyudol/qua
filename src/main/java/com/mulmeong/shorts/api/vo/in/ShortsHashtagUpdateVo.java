package com.mulmeong.shorts.api.vo.in;

import com.mulmeong.shorts.api.domain.model.Hashtag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;

@Getter
public class ShortsHashtagUpdateVo {

    private List<@Valid Hashtag> hashtags;

}
