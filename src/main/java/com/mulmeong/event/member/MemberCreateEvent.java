package com.mulmeong.event.member;

import com.mulmeong.member.read.member.document.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateEvent {
    private String memberUuid;
    private String nickname;
    private String profileImageUrl;
    private LocalDateTime createdAt;

    public Member toEntity() {
        return Member.builder()
                .memberUuid(memberUuid)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .createdAt(createdAt)
                .build();
    }
}
