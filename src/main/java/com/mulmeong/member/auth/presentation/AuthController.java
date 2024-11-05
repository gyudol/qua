package com.mulmeong.member.auth.presentation;

import com.mulmeong.member.auth.application.AuthService;
import com.mulmeong.member.auth.dto.in.NewAccessTokenRequestDto;
import com.mulmeong.member.auth.dto.in.SignUpAndInRequestDto;
import com.mulmeong.member.auth.vo.in.NewAccessTokenRequestVo;
import com.mulmeong.member.auth.vo.in.SignUpAndSignInRequestVo;
import com.mulmeong.member.auth.vo.out.SignUpAndSignInResponseVo;
import com.mulmeong.member.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증", description = "회원가입/로그인, 인증, 토큰 관련 API")
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    @Operation(summary = "회원가입, 로그인", description = "소셜 정보를 받아 회원가입이 되어있으면 로그인, 없으면 회원가입 후 로그인")
    public BaseResponse<SignUpAndSignInResponseVo> signUpAndSignIn(@Valid @RequestBody
                                                                   SignUpAndSignInRequestVo requestVo) {
        return new BaseResponse<>((authService.signUpAndSignIn(
                SignUpAndInRequestDto.toDto(requestVo))).toVo());
    }

    @PostMapping("/token/refresh")
    @Operation(summary = "액세스토큰 재발급", description = "Health check를 위한 API")
    public BaseResponse<String> createNewAccessToken(@RequestBody NewAccessTokenRequestVo requestVo) {

        return new BaseResponse<>(authService.createNewAccessToken(
                NewAccessTokenRequestDto.toDto(requestVo)));
    }
}
