package com.mulmeong.member.read.member.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Badge {
    private Long id;
    private String name;
    private String imageUrl;
    private String description;
}
