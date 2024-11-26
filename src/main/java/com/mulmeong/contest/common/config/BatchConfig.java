package com.mulmeong.contest.common.config;

import com.mulmeong.contest.entity.Contest;
import com.mulmeong.contest.entity.ContestPost;
import com.mulmeong.contest.entity.ContestResult;
import com.mulmeong.contest.entity.ContestVote;
import com.mulmeong.contest.infrastructure.ContestPostRepository;
import com.mulmeong.contest.infrastructure.ContestRepository;
import com.mulmeong.contest.infrastructure.ContestResultRepository;
import com.mulmeong.contest.infrastructure.ContestVoteRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.util.*;


@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
    private static final Logger log = LoggerFactory.getLogger(BatchConfig.class);
    private final JobRepository jobRepository;
    private final ContestRepository contestRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ContestResultRepository contestResultRepository;
    private final PlatformTransactionManager transactionManager;
    private final ContestVoteRepository contestVoteRepository;
    private final ContestPostRepository contestPostRepository;
    private static final String VOTE_COUNT_KEY = "contest:%d:post:votes";
    private static final String VOTER_SET_KEY = "contest:%d:post:%s:voters";


    @Bean
    public Job calculateContestRankJob() {
        return new JobBuilder("rankingJob", jobRepository)
                .start(calculateContestRankStep())
                .next(saveContestVotesStep())
                .build();
    }

    @Bean
    public Step calculateContestRankStep() {
        return new StepBuilder("rankStep", jobRepository)
                .<Contest, List<ContestResult>>chunk(10, transactionManager)
                .reader(contestReader())
                .processor(contestProcessor())
                .writer(contestWriter())
                .listener(loggingStepExecutionListener())
                .build();
    }

    @Bean
    public Step saveContestVotesStep() {
        return new StepBuilder("saveVotesStep", jobRepository)
                .<Contest, List<ContestVote>>chunk(10, transactionManager)
                .reader(contestPostReader())
                .processor(contestVoteProcessor()) // 투표자 처리
                .writer(contestVoteWriter()) // ContestVote 저장
                .listener(loggingStepExecutionListener())
                .build();
    }

    @Bean
    public ItemReader<Contest> contestReader() {
        return new ItemReader<>() {
            private final Iterator<Contest> iterator = contestRepository
                    .findByEndDate(LocalDate.now().minusDays(1)).iterator();

            @Override
            public Contest read() {
                return iterator.hasNext() ? iterator.next() : null;
            }
        };
    }

    @Bean
    public ItemReader<Contest> contestPostReader() {
        return new ItemReader<>() {
            private final Iterator<Contest> iterator = contestRepository
                    .findByEndDate(LocalDate.now().minusDays(1)).iterator();

            @Override
            public Contest read() {
                return iterator.hasNext() ? iterator.next() : null;
            }
        };
    }

    @Bean
    public ItemProcessor<Contest, List<ContestResult>> contestProcessor() {
        return contest -> {
            Long contestId = contest.getId();
            // Redis에서 Sorted Set으로 득표 데이터를 내림차순으로 조회
            Set<ZSetOperations.TypedTuple<String>> voteData = redisTemplate.opsForZSet()
                    .reverseRangeWithScores(String.format(VOTE_COUNT_KEY, contest.getId()), 0, -1);

            if (voteData == null || voteData.isEmpty()) {
                return null; // 득표 데이터가 없으면 결과 없음
            }

            List<ContestResult> results = new ArrayList<>();
            int rank = 1;
            // Redis에서 조회한 데이터로 순위 부여
            for (ZSetOperations.TypedTuple<String> entry : voteData) {
                String postUuid = entry.getValue();
                Long voteCount = Objects.requireNonNull(entry.getScore()).longValue();

                results.add(ContestResult.builder()
                        .contestId(contestId)
                        .postUuid(postUuid)
                        .voteCount(voteCount)
                        .ranking(rank++)
                        .build());
            }

            return results; // 순위 결과 리턴
        };
    }

    @Bean
    public ItemProcessor<Contest, List<ContestVote>> contestVoteProcessor() {
        return contest -> {
            Long contestId = contest.getId();
            List<ContestVote> contestVotes = new ArrayList<>();

            // 각 포스트에 대해 투표자 정보 저장
            List<ContestPost> posts = contestPostRepository.getAllByContestId(contestId);
            for (ContestPost post : posts) {
                String postUuid = post.getPostUuid();  // ContestPost에서 postUuid 가져오기

                // Redis에서 해당 포스트에 투표한 사람들을 조회
                Set<String> voters = redisTemplate.opsForSet()
                        .members(String.format(VOTER_SET_KEY, contestId, postUuid));

                if (voters != null) {
                    // 각 투표자에 대해 ContestVote 엔티티 생성
                    for (String memberUuid : voters) {
                        ContestVote contestVote = ContestVote.builder()
                                .contestId(contestId)
                                .postUuid(postUuid)
                                .memberUuid(memberUuid)
                                .build();
                        contestVotes.add(contestVote);  // 리스트에 추가
                    }
                }
            }

            return contestVotes;  // 생성된 ContestVote 리스트 반환
        };
    }

    @Bean
    public ItemWriter<List<ContestResult>> contestWriter() {
        return items -> {
            for (List<ContestResult> winners : items) {
                if (winners != null && !winners.isEmpty()) {
                    contestResultRepository.saveAll(winners);
                }
            }
        };
    }

    @Bean
    public ItemWriter<List<ContestVote>> contestVoteWriter() {
        return items -> {
            for (List<ContestVote> votes : items) {
                if (!items.isEmpty()) {
                    contestVoteRepository.saveAll(votes);
                }
            }
        };
    }

    @Bean
    public StepExecutionListener loggingStepExecutionListener() {
        return new StepExecutionListener() {
            @Override
            public void beforeStep(StepExecution stepExecution) {
                log.info("Starting step: {}", stepExecution.getStepName());
            }

            @Override
            public ExitStatus afterStep(StepExecution stepExecution) {
                log.info("Completed step: {} with status: {}", stepExecution.getStepName(), stepExecution.getStatus());
                return stepExecution.getExitStatus();
            }
        };
    }
}