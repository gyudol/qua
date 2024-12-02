package com.mulmeong.contest.dto.out;

import com.mulmeong.contest.entity.Contest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContestResponseDto {

    private Long contestId;
    private String contestName;
    private String imgUrl;


    @Builder
    public ContestResponseDto(
            Long contestId,
            String contestName,
            String imgUrl
    ) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.imgUrl = imgUrl;
    }

    public static ContestResponseDto toDto(Contest contest) {
        return ContestResponseDto.builder()
                .contestId(contest.getId())
                .contestName(contest.getName())
                .imgUrl(contest.getImgUrl())
                .build();
    }
}
