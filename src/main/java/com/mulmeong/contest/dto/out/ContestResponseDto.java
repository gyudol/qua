package com.mulmeong.contest.dto.out;

import com.mulmeong.contest.domain.entity.Contest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ContestResponseDto {

    private Long contestId;
    private String contestName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String imgUrl;


    @Builder
    public ContestResponseDto(
            Long contestId,
            String contestName,
            LocalDate startDate,
            LocalDate endDate,
            String imgUrl
    ) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imgUrl = imgUrl;
    }

    public static ContestResponseDto toDto(Contest contest) {
        return ContestResponseDto.builder()
                .contestId(contest.getId())
                .contestName(contest.getName())
                .startDate(contest.getStartDate())
                .endDate(contest.getEndDate())
                .imgUrl(contest.getImgUrl())
                .build();
    }
}
