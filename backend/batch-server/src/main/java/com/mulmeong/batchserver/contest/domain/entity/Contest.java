package com.mulmeong.batchserver.contest.domain.entity;

import com.mulmeong.batchserver.contest.domain.model.Kind;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Contest {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private Kind kind;
    private String name;
    private String description;
    private String imgUrl;
    private boolean status;
    private Long badgeId;

    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public Contest(
            Long id,
            Kind kind,
            String name,
            String description,
            String imgUrl,
            boolean status,
            Long badgeId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.status = status;
        this.badgeId = badgeId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
