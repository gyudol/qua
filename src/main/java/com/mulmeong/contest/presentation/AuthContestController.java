package com.mulmeong.contest.presentation;

import com.mulmeong.contest.application.ContestService;
import com.mulmeong.contest.common.response.BaseResponse;
import com.mulmeong.contest.common.utils.CursorPage;
import com.mulmeong.contest.dto.in.ContestQueryRequestDto;
import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRequestDto;
import com.mulmeong.contest.dto.out.ContestResponseDto;
import com.mulmeong.contest.vo.in.PostRequestVo;
import com.mulmeong.contest.vo.in.PostVoteRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "콘테스트 포스트 투표", description = "콘테스트 내 중복 투표 가능, 포스트 중복 투표 불가")
    @PostMapping("/posts/vote")
    public BaseResponse<Void> vote(
            @RequestBody PostVoteRequestVo postVoteRequestVo,
            @RequestHeader("Member-Uuid") String memberUuid
    ) {
        contestService.vote(PostVoteRequestDto.toDto(postVoteRequestVo), memberUuid);
        return new BaseResponse<>();
    }

    @GetMapping("/view")
    public BaseResponse<CursorPage<ContestResponseDto>> getCurrentContest(
            @RequestParam(defaultValue = "latest", required = false) String sortBy,
            @RequestParam(required = false, name = "nextCursor") String lastId,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo
    ) {

        return new BaseResponse<>(contestService.getCurrentContest(ContestQueryRequestDto.toDto(true, sortBy, lastId, pageNo, pageSize)));
    }

    @GetMapping("/history")
    public BaseResponse<CursorPage<ContestResponseDto>> getPastContest(
            @RequestParam(defaultValue = "latest", required = false) String sortBy,
            @RequestParam(required = false, name = "nextCursor") String lastId,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo
    ) {

        return new BaseResponse<>(contestService.getCurrentContest(ContestQueryRequestDto.toDto(false, sortBy, lastId, pageNo, pageSize)));
    }




}
