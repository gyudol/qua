package com.mulmeong.member.nickname.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckNicknameRequestDto {


    private String nickname;

    public static CheckNicknameRequestDto toDto(String nickname) {
        return CheckNicknameRequestDto.builder()
                .nickname(nickname)
                .build();
    }

    @Builder
    public CheckNicknameRequestDto(String memberUuid, String nickname) {
        this.nickname = nickname;
    }
}
