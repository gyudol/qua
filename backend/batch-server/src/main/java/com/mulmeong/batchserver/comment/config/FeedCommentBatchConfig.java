package com.mulmeong.batchserver.comment.config;

import com.mulmeong.batchserver.comment.domain.document.FeedComment;
import com.mulmeong.batchserver.comment.domain.document.FeedRecomment;
import com.mulmeong.batchserver.comment.infrastructure.repository.FeedCommentReadRepository;
import com.mulmeong.batchserver.comment.infrastructure.repository.FeedRecommentReadRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.DislikesRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.LikesRepository;
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
@EnableBatchProcessing
@Slf4j
public class FeedCommentBatchConfig {

    private final PlatformTransactionManager transactionManager;
    private final FeedCommentReadRepository feedCommentReadRepository;
    private final FeedRecommentReadRepository feedRecommentReadRepository;
    private final LikesRepository likesRepository;
    private final DislikesRepository dislikesRepository;
    private final MongoTemplate commentReadMongoTemplate;
    private final JobRepository jobRepository;
    private final JobLauncher jobLauncher;

    public FeedCommentBatchConfig(
            PlatformTransactionManager transactionManager,
            FeedCommentReadRepository feedCommentReadRepository,
            FeedRecommentReadRepository feedRecommentReadRepository,
            LikesRepository likesRepository,
            DislikesRepository dislikesRepository,
            @Qualifier("commentReadMongoTemplate") MongoTemplate commentReadMongoTemplate,
            JobRepository jobRepository,
            JobLauncher jobLauncher) {
        this.transactionManager = transactionManager;
        this.feedCommentReadRepository = feedCommentReadRepository;
        this.feedRecommentReadRepository = feedRecommentReadRepository;
        this.likesRepository = likesRepository;
        this.dislikesRepository = dislikesRepository;
        this.commentReadMongoTemplate = commentReadMongoTemplate;
        this.jobRepository = jobRepository;
        this.jobLauncher = jobLauncher;
    }

    @Scheduled(fixedRate = 300000)
    public void runFeedCommentRenewJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        log.info("Running job {}", jobParameters);

        jobLauncher.run(feedCommentRenewJob(), jobParameters);
    }


    @Bean
    public Job feedCommentRenewJob() {
        return new JobBuilder("feedCommentRenew", jobRepository)
                .start(feedCommentProcessingStep())
                .next(feedRecommentProcessingStep())
                .build();
    }

    @Bean
    public Step feedCommentProcessingStep() {
        return new StepBuilder("feedCommentProcessingStep", jobRepository)
                .<FeedComment, FeedComment>chunk(10, transactionManager)
                .reader(feedCommentReader())
                .processor(feedCommentProcessor())
                .writer(feedCommentWriter())
                .listener(feedCommentLoggingStepExecutionListener())
                .build();
    }

    @Bean
    public Step feedRecommentProcessingStep() {
        return new StepBuilder("feedRecommentProcessingStep", jobRepository)
                .<FeedRecomment, FeedRecomment>chunk(10, transactionManager)
                .reader(feedRecommentReader())
                .processor(feedRecommentProcessor())
                .writer(feedRecommentWriter())
                .listener(feedCommentLoggingStepExecutionListener())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<FeedComment> feedCommentReader() {
        return new IteratorItemReader<>(
                commentReadMongoTemplate.find(
                        new Query(),
                        FeedComment.class
                )
        );
    }

    @Bean
    public ItemProcessor<FeedComment, FeedComment> feedCommentProcessor() {
        return feedComment -> {
            // 실제 좋아요 수
            long actualLikeCount = likesRepository
                    .countByKindAndKindUuidAndStatus("feed_comment", feedComment.getCommentUuid(), true);
            // 실제 싫어요 수
            long actualDislikeCount = dislikesRepository
                    .countByKindAndKindUuidAndStatus("feed_comment", feedComment.getCommentUuid(), true);

            long actualRecommentCount = feedRecommentReadRepository.countByCommentUuid(feedComment.getCommentUuid());

            String cursor = String.format("%010d", actualLikeCount)
                    + String.format("%010d", 1000000000 - actualDislikeCount)
                    + String.format("%010d", actualRecommentCount)
                    + feedComment.getId();

            log.info("Processing feed comment: {}, Updated like count: {}, Updated dislike count: {}",
                    feedComment.getCommentUuid(), actualLikeCount, actualDislikeCount);

            return FeedComment.builder()
                    .id(feedComment.getId())
                    .feedUuid(feedComment.getFeedUuid())
                    .memberUuid(feedComment.getMemberUuid())
                    .commentUuid(feedComment.getCommentUuid())
                    .content(feedComment.getContent())
                    .isDeleted(feedComment.isDeleted())
                    .createdAt(feedComment.getCreatedAt())
                    .updatedAt(feedComment.getUpdatedAt())
                    .likeCount(actualLikeCount)
                    .dislikeCount(actualDislikeCount)
                    .recommentCount(actualRecommentCount)
                    .customCursor(cursor)
                    .build();
        };
    }

    @Bean
    public ItemWriter<FeedComment> feedCommentWriter() {
        return feedCommentReadRepository::saveAll;
    }

    @Bean
    @StepScope
    public ItemReader<FeedRecomment> feedRecommentReader() {
        return new IteratorItemReader<>(
                commentReadMongoTemplate.find(
                        new Query(
                                new Criteria().orOperator(
                                        Criteria.where("likeCount").gte(10L),
                                        Criteria.where("dislikeCount").gte(10L)
                                )
                        ),
                        FeedRecomment.class
                )
        );
    }

    @Bean
    public ItemProcessor<FeedRecomment, FeedRecomment> feedRecommentProcessor() {
        return feedRecomment -> {
            // 실제 좋아요 수
            long actualLikeCount = likesRepository
                    .countByKindAndKindUuidAndStatus("feed_recomment", feedRecomment.getRecommentUuid(), true);
            // 실제 싫어요 수
            long actualDislikeCount = dislikesRepository
                    .countByKindAndKindUuidAndStatus("feed_recomment", feedRecomment.getRecommentUuid(), true);

            log.info("Processing feed recomment: {}, Updated like count: {}, Updated dislike count: {}",
                    feedRecomment.getRecommentUuid(), actualLikeCount, actualDislikeCount);

            return FeedRecomment.builder()
                    .id(feedRecomment.getId())
                    .memberUuid(feedRecomment.getMemberUuid())
                    .commentUuid(feedRecomment.getCommentUuid())
                    .recommentUuid(feedRecomment.getRecommentUuid())
                    .content(feedRecomment.getContent())
                    .createdAt(feedRecomment.getCreatedAt())
                    .updatedAt(feedRecomment.getUpdatedAt())
                    .likeCount(actualLikeCount)
                    .dislikeCount(actualDislikeCount)
                    .build();
        };
    }

    @Bean
    public ItemWriter<FeedRecomment> feedRecommentWriter() {
        return feedRecommentReadRepository::saveAll;
    }

    @Bean
    public StepExecutionListener feedCommentLoggingStepExecutionListener() {
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
