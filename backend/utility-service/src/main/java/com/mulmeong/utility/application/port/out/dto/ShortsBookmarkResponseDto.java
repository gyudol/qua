package com.mulmeong.utility.application.port.out.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortsBookmarkResponseDto {

    private String id;
    private String memberUuid;
    private String shortsUuid;

}
