package com.mulmeong.batchserver.comment.config;

import com.mulmeong.batchserver.comment.domain.document.ShortsComment;
import com.mulmeong.batchserver.comment.domain.document.ShortsRecomment;
import com.mulmeong.batchserver.comment.infrastructure.repository.ShortsCommentReadRepository;
import com.mulmeong.batchserver.comment.infrastructure.repository.ShortsRecommentReadRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.DislikesRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@EnableBatchProcessing(dataSourceRef = "contestDataSource", transactionManagerRef = "contestTransactionManager")
@RequiredArgsConstructor
@Slf4j
public class ShortsCommentBatchConfig {

    private final PlatformTransactionManager transactionManager;
    private final ShortsCommentReadRepository shortsCommentReadRepository;
    private final ShortsRecommentReadRepository shortsRecommentReadRepository;
    private final LikesRepository likesRepository;
    private final DislikesRepository dislikesRepository;
    @Qualifier("commentReadMongoTemplate")
    private final  MongoTemplate commentReadMongoTemplate;
    private final JobRepository jobRepository;
    private final JobLauncher jobLauncher;

    @Scheduled(fixedRate = 300000)
    public void runShortsCommentRenewJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        log.info("Running job {}", jobParameters);

        jobLauncher.run(shortsCommentRenewJob(), jobParameters);
    }


    @Bean
    public Job shortsCommentRenewJob() {
        return new JobBuilder("shortsCommentRenewJob", jobRepository)
                .start(shortsCommentProcessingStep())
                .next(shortsRecommentProcessingStep())
                .build();
    }

    @Bean
    public Step shortsCommentProcessingStep() {
        return new StepBuilder("shortsCommentProcessingStep", jobRepository)
                .<ShortsComment, ShortsComment>chunk(10, transactionManager)
                .reader(shortsCommentReader())
                .processor(shortsCommentProcessor())
                .writer(shortsCommentWriter())
                .listener(shortsCommentLoggingStepExecutionListener())
                .build();
    }

    @Bean
    public Step shortsRecommentProcessingStep() {
        return new StepBuilder("shortsRecommentProcessingStep", jobRepository)
                .<ShortsRecomment, ShortsRecomment>chunk(10, transactionManager)
                .reader(shortsRecommentReader())
                .processor(shortsRecommentProcessor())
                .writer(shortsRecommentWriter())
                .listener(shortsCommentLoggingStepExecutionListener())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<ShortsComment> shortsCommentReader() {
        return new IteratorItemReader<>(
                commentReadMongoTemplate.find(
                        new Query(),
                        ShortsComment.class,
                        "shorts_comment"
                )
        );
    }

    @Bean
    public ItemProcessor<ShortsComment, ShortsComment> shortsCommentProcessor() {
        return shortsComment -> {
            // 실제 좋아요 수
            long actualLikeCount = likesRepository
                    .countByKindAndKindUuidAndStatus("shorts_comment", shortsComment.getCommentUuid(), true);
            // 실제 싫어요 수
            long actualDislikeCount = dislikesRepository
                    .countByKindAndKindUuidAndStatus("shorts_comment", shortsComment.getCommentUuid(), true);

            long actualRecommentCount = shortsRecommentReadRepository
                    .countByCommentUuid(shortsComment.getCommentUuid());

            String cursor = String.format("%010d", actualLikeCount)
                    + String.format("%010d", 1000000000 - actualDislikeCount)
                    + String.format("%010d", actualRecommentCount)
                    + shortsComment.getId();

            log.info("Processing shorts comment: {}, Updated like count: {}, Updated dislike count: {}",
                    shortsComment.getCommentUuid(), actualLikeCount, actualDislikeCount);

            return ShortsComment.builder()
                    .id(shortsComment.getId())
                    .shortsUuid(shortsComment.getShortsUuid())
                    .memberUuid(shortsComment.getMemberUuid())
                    .commentUuid(shortsComment.getCommentUuid())
                    .content(shortsComment.getContent())
                    .isDeleted(shortsComment.isDeleted())
                    .createdAt(shortsComment.getCreatedAt())
                    .updatedAt(shortsComment.getUpdatedAt())
                    .likeCount(actualLikeCount)
                    .dislikeCount(actualDislikeCount)
                    .recommentCount(actualRecommentCount)
                    .customCursor(cursor)
                    .build();
        };
    }

    @Bean
    public ItemWriter<ShortsComment> shortsCommentWriter() {
        return shortsCommentReadRepository::saveAll;
    }

    @Bean
    @StepScope
    public ItemReader<ShortsRecomment> shortsRecommentReader() {
        return new IteratorItemReader<>(
                commentReadMongoTemplate.find(
                        new Query(
                                new Criteria().orOperator(
                                        Criteria.where("likeCount").gte(10L),
                                        Criteria.where("dislikeCount").gte(10L)
                                )
                        ),
                        ShortsRecomment.class,
                        "shorts_recomment"
                )
        );
    }

    @Bean
    public ItemProcessor<ShortsRecomment, ShortsRecomment> shortsRecommentProcessor() {
        return shortsRecomment -> {
            // 실제 좋아요 수
            long actualLikeCount = likesRepository
                    .countByKindAndKindUuidAndStatus("shorts_recomment", shortsRecomment.getRecommentUuid(), true);
            // 실제 싫어요 수
            long actualDislikeCount = dislikesRepository
                    .countByKindAndKindUuidAndStatus("shorts_recomment", shortsRecomment.getRecommentUuid(), true);

            log.info("Processing shorts recomment: {}, Updated like count: {}, Updated dislike count: {}",
                    shortsRecomment.getRecommentUuid(), actualLikeCount, actualDislikeCount);

            return ShortsRecomment.builder()
                    .id(shortsRecomment.getId())
                    .memberUuid(shortsRecomment.getMemberUuid())
                    .commentUuid(shortsRecomment.getCommentUuid())
                    .recommentUuid(shortsRecomment.getRecommentUuid())
                    .content(shortsRecomment.getContent())
                    .createdAt(shortsRecomment.getCreatedAt())
                    .updatedAt(shortsRecomment.getUpdatedAt())
                    .likeCount(actualLikeCount)
                    .dislikeCount(actualDislikeCount)
                    .build();
        };
    }

    @Bean
    public ItemWriter<ShortsRecomment> shortsRecommentWriter() {
        return shortsRecommentReadRepository::saveAll;
    }

    @Bean
    public StepExecutionListener shortsCommentLoggingStepExecutionListener() {
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
