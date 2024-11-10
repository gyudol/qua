package com.mulmeong.admin.category.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CategoryCreateVo {

    @NotBlank(message = "카테고리 이름은 필수 항목입니다.")
    @Size(max = 20, message = "카테고리 이름은 20자 이하이어야 합니다.")
    private String categoryName;

    @NotBlank(message = "카테고리 뷰 타입은 필수 항목입니다.")
    @Pattern(regexp = "(?i)FEED|COMPACT", message = "뷰타입은 'FEED', 'COMPACT'중 하나여야 합니다.")
    private String viewType;
}
