package com.mulmeong.event.member;

import lombok.Getter;

@Getter
public class MemberGradeUpdateEvent {
    private String memberUuid;
    private Long gradeId;
}
