package com.mulmeong.contest.dto.in;

import com.mulmeong.contest.vo.in.PostVoteRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostVoteRequestDto {

    private Long contestId;
    private String postUuid;

    @Builder
    public PostVoteRequestDto(
            Long contestId,
            String postUuid
    ) {
        this.contestId = contestId;
        this.postUuid = postUuid;
    }

    public static PostVoteRequestDto toDto(PostVoteRequestVo postVoteRequestVo) {
        return PostVoteRequestDto.builder()
                .contestId(postVoteRequestVo.getContestId())
                .postUuid(postVoteRequestVo.getPostUuid())
                .build();
    }
}
