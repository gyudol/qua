package com.mulmeong.batchserver.shorts.config;

import com.mulmeong.batchserver.comment.infrastructure.repository.ShortsCommentReadRepository;
import com.mulmeong.batchserver.contest.config.ContestBatchConfig;
import com.mulmeong.batchserver.shorts.domain.document.ShortsRead;
import com.mulmeong.batchserver.shorts.infrastructure.repository.ShortsReadRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.DislikesRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableScheduling
@EnableBatchProcessing
@RequiredArgsConstructor
public class ShortsBatchConfig {

    private static final Logger log = LoggerFactory.getLogger(ContestBatchConfig.class);
    private final PlatformTransactionManager transactionManager;
    private final ShortsReadRepository shortsReadRepository;
    private final LikesRepository likesRepository;
    private final DislikesRepository dislikesRepository;
    private final ShortsCommentReadRepository shortsCommentReadRepository;
    private final @Qualifier("shortsReadMongoTemplate") MongoTemplate shortsReadMongoTemplate;
    private final JobRepository jobRepository;
    private final JobLauncher jobLauncher;

    @Scheduled(fixedRate = 300000)
    public void runShortsRenewJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        log.info("Running job {}", jobParameters);

        jobLauncher.run(shortsRenewJob(), jobParameters);
    }

    @Bean
    public Job shortsRenewJob() {
        return new JobBuilder("shortsRenew", jobRepository)
                .start(shortsLikeRenewStep())
                .next(shortsCommentCountRenewStep())
                .build();
    }

    @Bean
    public Step shortsLikeRenewStep() {
        return new StepBuilder("shortsLikeRenewStep", jobRepository)
                .<ShortsRead, ShortsRead>chunk(10, transactionManager)
                .reader(shortsReader())
                .processor(shortsProcessor())
                .writer(shortsWriter())
                .listener(shortsLoggingStepExecutionListener())
                .build();
    }

    @Bean
    public Step shortsCommentCountRenewStep() {
        return new StepBuilder("shortsCommentCountRenewStep", jobRepository)
                .<ShortsRead, ShortsRead>chunk(10, transactionManager)
                .reader(shortsAllReader())
                .processor(shortsCommentCountProcessor())
                .writer(shortsCommentCountWriter())
                .listener(shortsLoggingStepExecutionListener())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<ShortsRead> shortsReader() {
        return new IteratorItemReader<>(
                shortsReadMongoTemplate.find(
                        new Query(
                                new Criteria().orOperator(
                                        Criteria.where("likeCount").gte(10L),
                                        Criteria.where("dislikeCount").gte(10L)
                                )
                        ),
                        ShortsRead.class
                )
        );
    }

    @Bean
    public ItemProcessor<ShortsRead, ShortsRead> shortsProcessor() {
        return shortsRead -> {
            // 실제 좋아요 수
            long actualLikeCount = likesRepository.countByKindAndKindUuidAndStatus("shorts", shortsRead.getShortsUuid(), true);
            // 실제 싫어요 수
            long actualDislikeCount = dislikesRepository.countByKindAndKindUuidAndStatus("shorts", shortsRead.getShortsUuid(), true);


            log.info("Processing shorts: {}, Updated like count: {}, Updated dislike count: {}",
                    shortsRead.getShortsUuid(), actualLikeCount, actualDislikeCount);

            return ShortsRead.builder()
                    .id(shortsRead.getId())
                    .shortsUuid(shortsRead.getShortsUuid())
                    .memberUuid(shortsRead.getMemberUuid())
                    .title(shortsRead.getTitle())
                    .playtime(shortsRead.getPlaytime())
                    .visibility(shortsRead.getVisibility())
                    .hashtags(shortsRead.getHashtags())
                    .media(shortsRead.getMedia())
                    .likeCount(actualLikeCount)
                    .dislikeCount(actualDislikeCount)
                    .netLikes(actualLikeCount - actualDislikeCount)
                    .commentCount(shortsRead.getCommentCount())
                    .createdAt(shortsRead.getCreatedAt())
                    .updatedAt(shortsRead.getUpdatedAt())
                    .build();
        };
    }

    @Bean
    public ItemWriter<ShortsRead> shortsWriter() {
        return shortsReadRepository::saveAll;
    }

    @Bean
    @StepScope
    public ItemReader<ShortsRead> shortsAllReader() {
        return new IteratorItemReader<>(
                shortsReadMongoTemplate.find(
                        new Query(),
                        ShortsRead.class
                )
        );
    }

    @Bean
    public ItemProcessor<ShortsRead, ShortsRead> shortsCommentCountProcessor() {
        return shortsRead -> {

            long actualCommentCount = shortsCommentReadRepository.countByShortsUuid(shortsRead.getShortsUuid());

            log.info("Processing shorts: {}, Updated comment count: {}", shortsRead.getShortsUuid(), actualCommentCount);

            return ShortsRead.builder()
                    .id(shortsRead.getId())
                    .shortsUuid(shortsRead.getShortsUuid())
                    .memberUuid(shortsRead.getMemberUuid())
                    .title(shortsRead.getTitle())
                    .playtime(shortsRead.getPlaytime())
                    .visibility(shortsRead.getVisibility())
                    .hashtags(shortsRead.getHashtags())
                    .media(shortsRead.getMedia())
                    .likeCount(shortsRead.getLikeCount())
                    .dislikeCount(shortsRead.getDislikeCount())
                    .netLikes(shortsRead.getNetLikes())
                    .commentCount(actualCommentCount)
                    .createdAt(shortsRead.getCreatedAt())
                    .updatedAt(shortsRead.getUpdatedAt())
                    .build();
        };
    }

    @Bean
    public ItemWriter<ShortsRead> shortsCommentCountWriter() {
        return shortsReadRepository::saveAll;
    }

    @Bean
    public StepExecutionListener shortsLoggingStepExecutionListener() {
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
