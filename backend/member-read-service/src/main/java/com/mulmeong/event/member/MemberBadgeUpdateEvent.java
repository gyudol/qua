package com.mulmeong.event.member;

import lombok.Getter;

@Getter
public class MemberBadgeUpdateEvent {
    private String memberUuid;
    private Long badgeId;
    private String badgeName;
    private String badgeImageUrl;
    private String badgeDescription;
    private boolean equipped;

}
