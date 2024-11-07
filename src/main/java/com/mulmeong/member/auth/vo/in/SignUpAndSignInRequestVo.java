package com.mulmeong.member.auth.vo.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpAndSignInRequestVo {

    @NotBlank(message = "소셜 식별자는 필수 입력 항목입니다.")
    private String oauthId;

    @NotBlank(message = "소셜 제공자는 필수 입력 항목입니다.")
    private String oauthProvider;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}
