package com.mulmeong.event.contest.consume;

import com.mulmeong.contest.read.domain.document.ContestVote;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ContestVoteRecordEvent {

    private Long contestId;
    private String memberUuid;
    private String postUuid;

    public ContestVote toEntity(Long contestId, String memberUuid, String postUuid) {
        return ContestVote.builder()
                .contestId(contestId)
                .memberUuid(memberUuid)
                .postUuid(postUuid)
                .build();
    }


}
