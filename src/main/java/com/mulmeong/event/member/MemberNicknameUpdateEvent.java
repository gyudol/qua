package com.mulmeong.event.member;


import com.mulmeong.member.profile.dto.in.NicknameUpdateRequestDto;
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

    public static MemberNicknameUpdateEvent toEvent(NicknameUpdateRequestDto nicknameUpdateRequestDto) {
        return MemberNicknameUpdateEvent.builder()
                .memberUuid(nicknameUpdateRequestDto.getMemberUuid())
                .nickname(nicknameUpdateRequestDto.getNickname())
                .build();
    }
}

