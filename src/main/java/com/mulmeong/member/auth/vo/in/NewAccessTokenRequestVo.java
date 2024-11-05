package com.mulmeong.member.auth.vo.in;


import lombok.Getter;

@Getter
public class NewAccessTokenRequestVo {

    private String refreshToken;
    private String memberUuid;
}
