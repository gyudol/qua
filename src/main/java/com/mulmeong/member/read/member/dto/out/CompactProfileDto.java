package com.mulmeong.member.read.member.dto.out;

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
    private Long gradeId;
    private Long equippedBadgeId;

    public static CompactProfileDto fromDocument(Member member) {
        return CompactProfileDto.builder()
                .memberUuid(member.getMemberUuid())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .gradeId(member.getGradeId())
                .equippedBadgeId(member.getEquippedBadgeId())
                .build();
    }
}
