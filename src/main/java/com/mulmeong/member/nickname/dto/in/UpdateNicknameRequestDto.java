package com.mulmeong.member.nickname.dto.in;

import com.mulmeong.member.auth.domain.Member;
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
