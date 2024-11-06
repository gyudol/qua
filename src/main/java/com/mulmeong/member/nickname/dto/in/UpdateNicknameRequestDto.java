package com.mulmeong.member.nickname.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateNicknameRequestDto {

    private String memberUuid;

    private String nickname;

    public static UpdateNicknameRequestDto toDto(String memberUuid, String nickname) {
        return UpdateNicknameRequestDto.builder()
                .memberUuid(memberUuid)
                .nickname(nickname)
                .build();
    }

    @Builder
    public UpdateNicknameRequestDto(String memberUuid, String nickname) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
    }
}
