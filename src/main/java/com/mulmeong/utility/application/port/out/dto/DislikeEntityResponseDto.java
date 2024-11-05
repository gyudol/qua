package com.mulmeong.utility.application.port.out.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DislikeEntityResponseDto {

    private String id;
    private String memberUuid;
    private String kind;
    private String kindUuid;
    private boolean status;

}
