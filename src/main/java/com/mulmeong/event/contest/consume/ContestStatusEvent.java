package com.mulmeong.event.contest.consume;

import com.mulmeong.contest.domain.entity.Contest;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Getter
@NoArgsConstructor
public class ContestStatusEvent {

    private Long contestId;

    public Contest toEntity(Contest contest) {
        return Contest.builder()
                .id(contest.getId())
                .kind(contest.getKind())
                .name(contest.getName())
                .description(contest.getDescription())
                .imgUrl(contest.getImgUrl())
                .badgeId(contest.getBadgeId())
                .startDate(contest.getStartDate())
                .endDate(contest.getEndDate())
                .status(false)
                .build();
    }

}
