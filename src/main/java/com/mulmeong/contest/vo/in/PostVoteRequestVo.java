package com.mulmeong.contest.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PostVoteRequestVo {

    private Long contestId;
    private String postUuid;

}
