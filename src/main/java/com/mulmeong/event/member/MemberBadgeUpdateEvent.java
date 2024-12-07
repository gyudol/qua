package com.mulmeong.event.member;

import com.mulmeong.member.read.member.document.Badge;
import lombok.Getter;

@Getter
public class MemberBadgeUpdateEvent {
    private String memberUuid;
    private Long badgeId;
    private String badgeName;
    private String badgeImageUrl;
    private String badgeDescription;
    private boolean equipped;

    public Badge toEntity() {
        return Badge.builder()
                .id(badgeId)
                .name(badgeName)
                .imageUrl(badgeImageUrl)
                .description(badgeDescription)
                .build();
    }
}
