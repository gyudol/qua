package com.mulmeong.member.auth.application;

import com.mulmeong.member.auth.dto.in.NewAccessTokenRequestDto;
import com.mulmeong.member.auth.dto.in.SignUpAndInRequestDto;
import com.mulmeong.member.auth.dto.out.NewAccessTokenResponseDto;
import com.mulmeong.member.auth.dto.out.SignUpAndInResponseDto;


public interface AuthService {

    SignUpAndInResponseDto signUpAndSignIn(SignUpAndInRequestDto signUpAndInRequestDto);

    NewAccessTokenResponseDto createNewAccessToken(NewAccessTokenRequestDto newAccessTokenRequestDto);
}
