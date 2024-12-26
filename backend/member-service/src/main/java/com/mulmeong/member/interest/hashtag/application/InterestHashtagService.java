package com.mulmeong.member.interest.hashtag.application;

import com.mulmeong.member.interest.hashtag.dto.in.HashtagCreateDto;
import com.mulmeong.member.interest.hashtag.dto.in.HashtagUpdateDto;
import com.mulmeong.member.interest.hashtag.dto.out.HashtagDto;

import java.util.List;

public interface InterestHashtagService {

    void createInterestHashTag(HashtagCreateDto requestDto);

    List<HashtagDto> getInterestHashTagList(String memberUuid);

    void updateInterestHashTag(HashtagUpdateDto requestDto);
}
