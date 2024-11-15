package com.mulmeong.contest.presentation;

import com.mulmeong.contest.application.ContestService;
import com.mulmeong.contest.common.response.BaseResponse;
import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.vo.in.ContestRequestVo;
import com.mulmeong.contest.vo.in.PostRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/v1/contests")
public class AdminContestController {

    private final ContestService contestService;

    @Operation(summary = "콘테스트 개최", description = "Contest-Service")
    @PostMapping("/open")
    public BaseResponse<Void> openContest(@RequestBody ContestRequestVo contestRequestVo) {

        contestService.openContest(ContestRequestDto.toDto(contestRequestVo));

        return new BaseResponse<>();
    }

}
