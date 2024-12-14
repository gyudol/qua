package com.mulmeong.event.report;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ReportApproveEvent {
    private String memberUuid;
    private String targetUuid;
    private ReportType reportType;

    public enum ReportType {
        MEMBER,
        FEED,
        SHORTS,
        COMMENT,
        RECOMMENT
    }
}
