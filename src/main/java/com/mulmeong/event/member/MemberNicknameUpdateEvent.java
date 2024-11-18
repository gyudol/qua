package com.mulmeong.event.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class MemberNicknameUpdateEvent {
    private String memberUuid;
    private String nickname;
}

