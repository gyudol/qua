package com.mulmeong.notification.client.shorts;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShortsDto {
    private String shortsUuid;
    private String memberUuid;
    private String title;
}
