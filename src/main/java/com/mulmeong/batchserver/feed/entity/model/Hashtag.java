package com.mulmeong.batchserver.feed.entity.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {

    @NotBlank(message = "해시태그명은 필수 입력 항목입니다.")
    private String name;

}
