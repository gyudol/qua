package com.mulmeong.contest.dto.in;

import com.mulmeong.contest.entity.Contest;
import com.mulmeong.contest.entity.Kind;
import com.mulmeong.contest.vo.in.ContestRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
public class ContestRequestDto {

    private Kind kind;
    private String name;
    private String description;
    private String imgUrl;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public ContestRequestDto(
            Kind kind,
            String name,
            String description,
            String imgUrl,
            LocalDate startDate,
            LocalDate endDate
    ) {
        this.kind = kind;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Contest toEntity() {
        return Contest.builder()
                .kind(kind)
                .name(name)
                .description(description)
                .imgUrl(imgUrl)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    public static ContestRequestDto toDto(ContestRequestVo contestRequestVo) {
        return ContestRequestDto.builder()
                .kind(contestRequestVo.getKind())
                .name(contestRequestVo.getName())
                .description(contestRequestVo.getDescription())
                .imgUrl(contestRequestVo.getImgUrl())
                .startDate(contestRequestVo.getStartDate())
                .endDate(contestRequestVo.getEndDate())
                .build();
    }
}
