package com.mulmeong.utility.adapter.in.web.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ReactionRequestVo {

    private String memberUuid;
    private String kind;
    private String kindUuid;
    private Integer status;

}
