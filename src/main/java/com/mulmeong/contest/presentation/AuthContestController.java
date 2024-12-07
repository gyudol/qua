package com.mulmeong.contest.presentation;

import com.mulmeong.contest.application.ContestService;
import com.mulmeong.contest.common.response.BaseResponse;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRequestDto;
import com.mulmeong.contest.vo.in.PostRequestVo;
import com.mulmeong.contest.vo.in.PostVoteRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "auth-contest", description = "콘테스트 회원 기능")
@RequestMapping("/auth/v1/contests")
public class AuthContestController {

    private final ContestService contestService;

    @Operation(summary = "콘테스트 신청(Post 등록)", description = "Contest-Service")
    @PostMapping("/{contestId}/apply")
    public BaseResponse<Void> applyContest(
            @RequestHeader("Member-Uuid") String memberUuid,
            @PathVariable Long contestId,
            @RequestBody PostRequestVo postRequestVo) {

        contestService.applyContest(PostRequestDto.toDto(memberUuid, contestId, postRequestVo));

        return new BaseResponse<>();
    }

    @Operation(summary = "콘테스트 포스트 투표", description = "콘테스트 내 중복 투표 가능, 포스트 중복 투표 불가")
    @PostMapping("/posts/vote")
    public BaseResponse<Void> vote(
            @RequestBody PostVoteRequestVo postVoteRequestVo,
            @RequestHeader("Member-Uuid") String memberUuid
    ) {
        contestService.vote(PostVoteRequestDto.toDto(postVoteRequestVo), memberUuid);
        return new BaseResponse<>();
    }


}
