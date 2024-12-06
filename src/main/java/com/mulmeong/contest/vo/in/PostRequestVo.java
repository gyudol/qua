package com.mulmeong.contest.vo.in;

import com.mulmeong.contest.entity.MediaType;
import lombok.Getter;

@Getter
public class PostRequestVo {

    private String mediaUrl;
    private MediaType mediaType;

}
