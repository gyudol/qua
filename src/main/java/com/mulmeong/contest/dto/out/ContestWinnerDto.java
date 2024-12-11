package com.mulmeong.contest.dto.out;

import com.mulmeong.contest.domain.entity.ContestResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContestWinnerDto {

    private String memberUuid;
    private String postUuid;
    private Long badgeId;
    private Long voteCount;
    private Byte ranking;

    public static ContestWinnerDto toDto(ContestResult contestResult) {
        return ContestWinnerDto.builder()
                .memberUuid(contestResult.getMemberUuid())
                .postUuid(contestResult.getPostUuid())
                .badgeId(contestResult.getBadgeId())
                .voteCount(contestResult.getVoteCount())
                .ranking(contestResult.getRanking())
                .build();
    }
}