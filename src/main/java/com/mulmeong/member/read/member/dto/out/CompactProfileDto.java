package com.mulmeong.member.read.member.dto.out;

import com.mulmeong.member.read.member.document.Badge;
import com.mulmeong.member.read.member.document.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CompactProfileDto {
    private String memberUuid;
    private String nickname;
    private String profileImageUrl;
    private String grade;
    private Badge equippedBadge;

    public static CompactProfileDto fromDocument(Member member) {
        return CompactProfileDto.builder()
                .memberUuid(member.getMemberUuid())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .grade(member.getGrade())
                .equippedBadge(member.getEquippedBadge())
                .build();
    }
}
