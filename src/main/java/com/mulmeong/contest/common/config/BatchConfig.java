package com.mulmeong.contest.common.config;

import com.mulmeong.contest.entity.Contest;
import com.mulmeong.contest.entity.ContestWinner;
import com.mulmeong.contest.infrastructure.ContestPostRepository;
import com.mulmeong.contest.infrastructure.ContestRepository;
import com.mulmeong.contest.infrastructure.ContestWinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
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

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.*;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
    private final JobRepository jobRepository;
    private final ContestRepository contestRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ContestWinnerRepository contestWinnerRepository;
    private final PlatformTransactionManager transactionManager;
    private static final String VOTE_COUNT_KEY = "contest:%d:post:votes";
    private static final String VOTER_SET_KEY = "contest:%d:post:%s:voters";


    @Bean
    public Job calculateContestRankJob(JobLauncher jobLauncher) {
        return new JobBuilder("calculateContestRankJob", jobRepository)
                .start(calculateContestRankStep())
                .build();
    }

    @Bean
    public Step calculateContestRankStep() {
        return new StepBuilder("calculateContestRankStep", jobRepository)
                .<Contest, List<ContestWinner>>chunk(10, transactionManager) // 배치 크기 10
                .reader(contestReader())
                .processor(contestProcessor())
                .writer(contestWriter())
                .faultTolerant() // 예외 발생 시 재시도
                .skip(Exception.class) // 예외가 발생하면 건너뛰기
                .skipLimit(5) // 최대 5번까지 예외 발생 시 건너뛰기
                .build();
    }

    @Bean
    public ItemReader<Contest> contestReader() {
        return new ItemReader<>() {
            private final Iterator<Contest> iterator = contestRepository.findByEndDate(LocalDate.now().minusDays(1)).iterator();

            @Override
            public Contest read() {
                return iterator.hasNext() ? iterator.next() : null;
            }
        };
    }

    @Bean
    public ItemProcessor<Contest, List<ContestWinner>> contestProcessor() {
        return contest -> {
            Long contestId = contest.getId();
            // Redis에서 Sorted Set으로 득표 데이터를 내림차순으로 조회
            Set<ZSetOperations.TypedTuple<String>> voteData = redisTemplate.opsForZSet()
                    .reverseRangeWithScores(String.format(VOTE_COUNT_KEY, contest.getId()), 0, 99); // 100개까지만 조회

            if (voteData == null || voteData.isEmpty()) {
                return null; // 득표 데이터가 없으면 결과 없음
            }

            List<ContestWinner> results = new ArrayList<>();
            byte rank = 1;
            // Redis에서 조회한 데이터로 순위 부여
            for (ZSetOperations.TypedTuple<String> entry : voteData) {
                String postUuid = entry.getValue();
                Long voteCount = Objects.requireNonNull(entry.getScore()).longValue();

                results.add(ContestWinner.builder()
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
    public ItemWriter<List<ContestWinner>> contestWriter() {
        return items -> {
            for (List<ContestWinner> winners : items) {
                if (winners != null && !winners.isEmpty()) {
                    contestWinnerRepository.saveAll(winners);
                }
            }
        };
    }
}