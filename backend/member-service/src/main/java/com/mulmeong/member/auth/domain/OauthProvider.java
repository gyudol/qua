package com.mulmeong.member.auth.domain;

public enum OauthProvider {
    KAKAO, NAVER;

    public static boolean isSupported(String provider) {

        for (OauthProvider p : OauthProvider.values()) {
            if (p.name().equalsIgnoreCase(provider.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}