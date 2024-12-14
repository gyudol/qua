package com.mulmeong.batchserver.presentation;

import com.mulmeong.batchserver.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final JobLauncher jobLauncher;
    private final Job voteResultJob;
    private final Job voteRenewJob;
    private final Job feedRenewJob;
    private final Job shortsRenewJob;


    @Operation(summary = "투표수 갱신 테스트", description = "현재 스케줄러 설정 시간 5분, 해당 api로 테스트 가능")
    @GetMapping("/renewTest")
    public BaseResponse<Void> renewJob() {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(voteRenewJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException
                 | JobRestartException
                 | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
        return new BaseResponse<>();
    }

    @Operation(summary = "콘테스트 정산(마감) 테스트", description = "투표 기록, 순위 결정, 콘테스트 상태 변경(false)")
    @GetMapping("/resultTest")
    public BaseResponse<Void> runVoteResultJob() {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(voteResultJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException
                 | JobRestartException
                 | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
        return new BaseResponse<>();
    }

    @Operation(summary = "피드 갱신 테스트", description = "좋아요, 싫어요 10개 이상일 경우 5분마다 갱신, 댓글은 상관 없이 5분 마다 갱신")
    @GetMapping("/feedRenewTest")
    public BaseResponse<Void> runFeedRenewJob() {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(feedRenewJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException
                 | JobRestartException
                 | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
        return new BaseResponse<>();
    }

    @Operation(summary = "쇼츠 갱신 테스트", description = "좋아요, 싫어요 10개 이상일 경우 5분마다 갱신, 댓글은 상관 없이 5분 마다 갱신")
    @GetMapping("/shortsRenewTest")
    public BaseResponse<Void> runShortsRenewJob() {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(shortsRenewJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException
                 | JobRestartException
                 | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
        return new BaseResponse<>();
    }
}