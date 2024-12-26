package com.mulmeong.event.contest.consume;

import com.mulmeong.contest.domain.entity.ContestResult;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ContestVoteResultEvent {

    private Long contestId;
    private String memberUuid;
    private String postUuid;
    private Long badgeId;
    private Long voteCount;
    private Byte ranking;

    public ContestResult toEntity(
            Long contestId,
            String memberUuid,
            String postUuid,
            Long badgeId,
            Long voteCount,
            Byte ranking
    ) {
        return ContestResult.builder()
                .contestId(contestId)
                .memberUuid(memberUuid)
                .postUuid(postUuid)
                .badgeId(badgeId)
                .voteCount(voteCount)
                .ranking(ranking)
                .build();
    }

}
