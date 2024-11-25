package com.mulmeong.member.profile.dto.in;

import com.mulmeong.member.auth.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NicknameUpdateRequestDto {

    private String memberUuid;
    private String nickname;

    public static NicknameUpdateRequestDto toDto(String memberUuid, String nickname) {
        return NicknameUpdateRequestDto.builder()
                .memberUuid(memberUuid)
                .nickname(nickname)
                .build();
    }

    @Builder
    public NicknameUpdateRequestDto(String memberUuid, String nickname) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
    }

    public Member toEntity(Member member) {
        return Member.builder()
                .id(member.getId())
                .memberUuid(member.getMemberUuid())
                .oauthId(member.getOauthId())
                .oauthProvider(member.getOauthProvider())
                .email(member.getEmail())
                .nickname(this.nickname)
                .createdAt(member.getCreatedAt())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }
}
