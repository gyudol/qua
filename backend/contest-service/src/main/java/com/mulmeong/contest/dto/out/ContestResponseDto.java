package com.mulmeong.contest.dto.out;

import com.mulmeong.contest.domain.entity.Contest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ContestResponseDto {

    private Long contestId;
    private String contestName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String imgUrl;
    private List<ContestWinnerDto> winners;

    @Builder
    public ContestResponseDto(
            Long contestId,
            String contestName,
            LocalDate startDate,
            LocalDate endDate,
            String imgUrl,
            List<ContestWinnerDto> winners
    ) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imgUrl = imgUrl;
        this.winners = winners;
    }

    public static ContestResponseDto toDto(Contest contest, List<ContestWinnerDto> winners) {
        return ContestResponseDto.builder()
                .contestId(contest.getId())
                .contestName(contest.getName())
                .startDate(contest.getStartDate())
                .endDate(contest.getEndDate())
                .imgUrl(contest.getImgUrl())
                .winners(winners)
                .build();
    }
}
