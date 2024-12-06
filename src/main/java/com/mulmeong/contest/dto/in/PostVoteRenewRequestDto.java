package com.mulmeong.contest.dto.in;

import com.mulmeong.contest.vo.in.PostVoteRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostVoteRenewRequestDto {

    private Long contestId;

    @Builder
    public PostVoteRenewRequestDto(
            Long contestId
    ) {
        this.contestId = contestId;
    }

    public static PostVoteRenewRequestDto toDto(PostVoteRequestVo postVoteRequestVo) {
        return PostVoteRenewRequestDto.builder()
                .contestId(postVoteRequestVo.getContestId())
                .build();
    }
}
