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
    private final JobLauncher jobLauncher;
    private final Job calculateContestRankJob;

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

    @Operation(summary = "콘테스트 마감 테스트용", description = "순위 집계는 콘테스트 종료 후 스케줄러로 자동 실행")
    @PostMapping("/{contestId}/finalize")
    public BaseResponse<Void> finalizeContest(@PathVariable Long contestId) {
        contestService.calculateWinners(contestId);
        return new BaseResponse<>();
    }

    @PostMapping("/calculateContestRank/{contestId}")
    public ResponseEntity<String> runCalculateContestRankJob(@PathVariable Long contestId) {
        try {
            // 배치 작업 실행
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("contestId", contestId) // 예시로 contestId를 파라미터로 추가
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(calculateContestRankJob, jobParameters);

            // 배치 작업이 성공적으로 실행된 경우
            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                return ResponseEntity.ok("Batch job completed successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Batch job failed with status: " + jobExecution.getStatus());
            }
        } catch (Exception e) {
            // 예외가 발생한 경우
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while running batch job: " + e.getMessage());
        }
    }


}
