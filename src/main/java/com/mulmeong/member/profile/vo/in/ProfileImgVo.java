package com.mulmeong.member.profile.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImgVo {

    @NotBlank(message = "프로필 이미지 URL은 필수 항목입니다.")
    @Size(max = 2083, message = "프로필 이미지 URL은 2083자 이하이어야 합니다.")
    private String profileImgUrl;
}
