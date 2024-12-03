package com.mulmeong.event.contest;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ContestVoteResultEvent {
    private Long contestId;
    private Byte ranking;
    private String memberUuid;
}