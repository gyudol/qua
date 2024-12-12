package com.mulmeong.notification.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadInfoVo {
    private boolean read;
    private Integer count;
}
