package com.mulmeong.contest.vo.in;

import com.mulmeong.contest.domain.model.Kind;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class ContestRequestVo {

    private Kind kind;
    private String name;
    private String description;
    private String imgUrl;
    private Long badgeId;
    private LocalDate startDate;
    private LocalDate endDate;

}
