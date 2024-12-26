package com.mulmeong.contest.presentation;

import com.mulmeong.contest.application.ContestService;
import com.mulmeong.contest.common.response.BaseResponse;
import com.mulmeong.contest.common.utils.CursorPage;
import com.mulmeong.contest.dto.in.ContestQueryRequestDto;
import com.mulmeong.contest.dto.out.ContestResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "contest", description = "콘테스트 공통")
@RequestMapping("/v1/contests")
public class ContestController {

    private final ContestService contestService;

    @Operation(summary = "현재 콘테스트 조회", description = "현재 진행 중인 콘테스트 목록 조회(최신순(latest), 과거순(oldest))")
    @GetMapping("/view")
    public BaseResponse<CursorPage<ContestResponseDto>> getCurrentContest(
            @RequestParam(defaultValue = "latest", required = false) String sortBy,
            @RequestParam(required = false, name = "nextCursor") String lastId,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo
    ) {

        return new BaseResponse<>(contestService.getContests(
                ContestQueryRequestDto.toDto(true, sortBy, lastId, pageSize, pageNo)));
    }

    @Operation(summary = "과거 콘테스트 조회", description = "마감 된 콘테스트 목록 조회(최신순(latest), 과거순(oldest))")
    @GetMapping("/history")
    public BaseResponse<CursorPage<ContestResponseDto>> getPastContest(
            @RequestParam(defaultValue = "latest", required = false) String sortBy,
            @RequestParam(required = false, name = "nextCursor") String lastId,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo
    ) {

        return new BaseResponse<>(contestService.getContests(
                ContestQueryRequestDto.toDto(false, sortBy, lastId, pageSize, pageNo)));
    }
}
