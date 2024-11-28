package com.mulmeong.contest.application;

import com.mulmeong.contest.common.exception.BaseException;
import com.mulmeong.contest.common.response.BaseResponseStatus;
import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRequestDto;
import com.mulmeong.contest.entity.Contest;
import com.mulmeong.contest.entity.ContestPost;
import com.mulmeong.contest.infrastructure.*;
import com.mulmeong.event.contest.produce.ContestPostCreateEvent;
import com.mulmeong.event.contest.produce.ContestPostUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContestServiceImpl implements ContestService {

    private final EventPublisher eventPublisher;
    private final ContestRepository contestRepository;
    private final ContestPostRepository contestPostRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final JobLauncher jobLauncher;
    private final Job rankingJob;

    private static final String VOTER_SET_KEY = "contest:%d:post:%s:voters";
    private static final String VOTE_COUNT_KEY = "contest:%d:post:votes";

    @Override
    public void openContest(ContestRequestDto dto) {
        contestRepository.save(dto.toEntity());
    }

    @Override
    public void applyContest(PostRequestDto dto) {
        if (contestPostRepository.existsByContestIdAndMemberUuid(dto.getContestId(), dto.getMemberUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_POST);
        }
        ContestPost contestPost = contestPostRepository.save(dto.toEntity());
        eventPublisher.send(ContestPostCreateEvent.toDto(contestPost));
    }

    @Override
    public void vote(PostVoteRequestDto voteRequestDto, String memberUuid) {
        String voterSetKey = String.format(VOTER_SET_KEY,
                voteRequestDto.getContestId(),
                voteRequestDto.getPostUuid());

        String voteCountKey = String.format(VOTE_COUNT_KEY,
                voteRequestDto.getContestId());

        // 중복 투표 체크: 이미 투표한 유저가 있으면 중복으로 투표할 수 없도록 처리
        Long isNewVote = redisTemplate.opsForSet().add(voterSetKey, memberUuid);
        if (isNewVote == 0) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_VOTE);
        }

        redisTemplate.opsForZSet().incrementScore(voteCountKey, voteRequestDto.getPostUuid(), 1);

//        eventPublisher.send(ContestPostUpdateEvent.toDto(voteRequestDto.getPostUuid()));

    }

    @Override
    public void voteRenew() {
        LocalDate currentDate = LocalDate.now();
        List<Contest> activeContests = contestRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(currentDate, currentDate);
        log.info("contest = {}", activeContests);

        for (Contest contest : activeContests) {
            Long contestId = contest.getId();
            Set<String> voteCountKeys = redisTemplate.keys(String.format(VOTE_COUNT_KEY, contestId));

            if (voteCountKeys != null) {
                for (String voteCountKey : voteCountKeys) {
                    // Sorted Set의 모든 값과 점수를 가져옴
                    Set<ZSetOperations.TypedTuple<String>> voteData = redisTemplate.opsForZSet().rangeWithScores(voteCountKey, 0, -1);

                    if (voteData != null) {
                        for (ZSetOperations.TypedTuple<String> entry : voteData) {
                            String postUuid = entry.getValue();
                            Integer score = Objects.requireNonNull(entry.getScore()).intValue();

                            // 이벤트 발행
                            eventPublisher.send(ContestPostUpdateEvent.toDto(postUuid, score));
                        }
                    }

                    // 키 삭제
                    redisTemplate.delete(voteCountKey);
                }
            }
        }
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")    // 매일 정각 배치 작업 실행
    public void selectWinners()
            throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder()
                .toJobParameters();

        jobLauncher.run(rankingJob, jobParameters);

    }
}
