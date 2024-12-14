package com.mulmeong.notification.client.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
    private String memberUuid;
    private String nickname;
    private String profileImageUrl;
}
