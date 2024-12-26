package com.mulmeong.member.interest.hashtag.dto.out;

import com.mulmeong.member.interest.hashtag.domain.InterestHashtag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class HashtagDto {

    private String name;

    public static HashtagDto fromEntity(InterestHashtag interestHashtag) {
        return HashtagDto.builder()
                .name(interestHashtag.getHashtagName())
                .build();
    }
}
