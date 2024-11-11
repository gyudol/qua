package com.mulmeong.utility.application.port.out.dto;

import com.mulmeong.utility.domain.model.Likes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikesResponseDto {

    private String memberUuid;
    private String kind;
    private String kindUuid;
    private boolean status;


}
