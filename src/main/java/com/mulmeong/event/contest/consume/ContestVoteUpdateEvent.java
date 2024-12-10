package com.mulmeong.event.contest.consume;

import com.mulmeong.contest.read.domain.document.ContestPost;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ContestVoteUpdateEvent {

    private Long contestId;
    private String postUuid;
    private Integer count;

    public ContestPost toEntity(ContestPost contestPost, Integer count) {
        return ContestPost.builder()
                .id(contestPost.getId())
                .postUuid(postUuid)
                .contestId(contestPost.getContestId())
                .memberUuid(contestPost.getMemberUuid())
                .media(contestPost.getMedia())
                .createdAt(contestPost.getCreatedAt())
                .voteCount(contestPost.getVoteCount() + count)
                .build();
    }

}
