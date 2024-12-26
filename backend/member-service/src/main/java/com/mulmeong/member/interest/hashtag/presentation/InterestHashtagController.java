package com.mulmeong.member.interest.hashtag.presentation;

import com.mulmeong.member.common.response.BaseResponse;
import com.mulmeong.member.interest.hashtag.application.InterestHashtagService;
import com.mulmeong.member.interest.hashtag.dto.in.HashtagCreateDto;
import com.mulmeong.member.interest.hashtag.dto.in.HashtagUpdateDto;
import com.mulmeong.member.interest.hashtag.dto.out.HashtagDto;
import com.mulmeong.member.interest.hashtag.vo.in.HashtagCreateVo;
import com.mulmeong.member.interest.hashtag.vo.in.HashtagUpdateVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "관심 해시태그", description = "관심 해시태그 API")
@RequiredArgsConstructor
@RequestMapping("v1/members")
@RestController
public class InterestHashtagController {

    private final InterestHashtagService interestHashtagService;

    @Operation(summary = "관심 해시태그 생성", description = "관심 해시태그 생성")
    @PostMapping("/{memberUuid}/interests/hashtags")
    public BaseResponse<Void> createInterestHashtag(@PathVariable String memberUuid,
                                                    @RequestBody HashtagCreateVo requestVo) {
        interestHashtagService.createInterestHashTag(HashtagCreateDto.toDto(memberUuid, requestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "관심 해시태그 조회", description = "관심 해시태그 조회")
    @GetMapping("/{memberUuid}/interests/hashtags")
    public BaseResponse<List<HashtagDto>> getInterestHashtag(@PathVariable String memberUuid) {

        return new BaseResponse<>(interestHashtagService.getInterestHashTagList(memberUuid));
    }

    @Operation(summary = "관심 해시태그 수정", description = "관심 해시태그 수정")
    @PutMapping("/{memberUuid}/interests/hashtags")
    public BaseResponse<Void> updateInterestHashtag(@PathVariable String memberUuid,
                                                     @RequestBody HashtagUpdateVo requestVo) {
        interestHashtagService.updateInterestHashTag(HashtagUpdateDto.toDto(memberUuid, requestVo));
        return new BaseResponse<>();
    }


}
