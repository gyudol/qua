package com.mulmeong.member.profile.dto.in;

import com.mulmeong.member.auth.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImgUpdateRequestDto {
    private String memberUuid;
    private String profileImgUrl;

    public static ProfileImgUpdateRequestDto toDto(String memberUuid, String profileImgUrl) {
        return new ProfileImgUpdateRequestDto(memberUuid, profileImgUrl);
    }

    public Member toEntity(Member member) {
        return Member.builder()
                .id(member.getId())
                .memberUuid(member.getMemberUuid())
                .oauthId(member.getOauthId())
                .oauthProvider(member.getOauthProvider())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .profileImageUrl(this.profileImgUrl)
                .build();
    }
}
