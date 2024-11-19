package com.mulmeong.contest.presentation;

import com.mulmeong.contest.application.ContestService;
import com.mulmeong.contest.common.response.BaseResponse;
import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRequestDto;
import com.mulmeong.contest.entity.ContestWinner;
import com.mulmeong.contest.vo.in.ContestRequestVo;
import com.mulmeong.contest.vo.in.PostRequestVo;
import com.mulmeong.contest.vo.in.PostVoteRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
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

    @Operation(summary = "콘테스트 포스트 투표", description = "Contest-Service")
    @PostMapping("/posts/vote")
    public BaseResponse<Void> vote(
            @RequestBody PostVoteRequestVo postVoteRequestVo,
            @RequestHeader("Member-Uuid") String memberUuid
    ) {
        contestService.vote(PostVoteRequestDto.toDto(postVoteRequestVo), memberUuid);
        return new BaseResponse<>();
    }

    @Operation(summary = "콘테스트 마감 테스트용(스케줄러로 처리)", description = "Contest-Service")
    @PostMapping("/{contestId}/finalize")
    public BaseResponse<Void> finalizeContest(@PathVariable Long contestId) {
        contestService.calculateWinners(contestId);
        return new BaseResponse<>();
    }


}
