package com.mulmeong.member.profile.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NicknameVo {

    @NotBlank(message = "닉네임은 필수 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣#]*$", message = "닉네임은 영문, 숫자, 한글, #만 포함할 수 있습니다.")
    @Size(min = 3, max = 15, message = "닉네임은 3자 이상 15자 이하이어야 합니다.")
    private String nickname;

}
