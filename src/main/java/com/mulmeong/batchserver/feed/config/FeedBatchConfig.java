package com.mulmeong.batchserver.feed.config;

import com.mulmeong.batchserver.comment.infrastructure.repository.FeedCommentReadRepository;
import com.mulmeong.batchserver.feed.application.FeedKafkaPublisher;
import com.mulmeong.batchserver.feed.domain.document.FeedRead;
import com.mulmeong.batchserver.utility.infrastructure.repository.DislikesRepository;
import com.mulmeong.batchserver.utility.infrastructure.repository.LikesRepository;
import com.mulmeong.event.utility.produce.FeedMetricsUpdateEvent;
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
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
@Slf4j
public class FeedBatchConfig {

    private final PlatformTransactionManager transactionManager;
    private final LikesRepository likesRepository;
    private final DislikesRepository dislikesRepository;
    private final FeedCommentReadRepository feedCommentReadRepository;
    private final MongoTemplate feedReadMongoTemplate;
    private final JobRepository jobRepository;
    private final JobLauncher jobLauncher;
    private final FeedKafkaPublisher feedKafkaPublisher;

    public FeedBatchConfig(
            PlatformTransactionManager transactionManager,
            LikesRepository likesRepository,
            DislikesRepository dislikesRepository,
            FeedCommentReadRepository feedCommentReadRepository,
            @Qualifier("feedReadMongoTemplate") MongoTemplate feedReadMongoTemplate,
            FeedKafkaPublisher feedKafkaPublisher,
            JobRepository jobRepository,
            JobLauncher jobLauncher) {
        this.transactionManager = transactionManager;
        this.likesRepository = likesRepository;
        this.dislikesRepository = dislikesRepository;
        this.feedCommentReadRepository = feedCommentReadRepository;
        this.feedReadMongoTemplate = feedReadMongoTemplate;
        this.feedKafkaPublisher = feedKafkaPublisher;
        this.jobRepository = jobRepository;
        this.jobLauncher = jobLauncher;
    }

    @Scheduled(fixedRate = 300000)
    public void runFeedRenewJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        log.info("Running job {}", jobParameters);

        jobLauncher.run(feedRenewJob(), jobParameters);
    }

    @Bean
    public Job feedRenewJob() {
        return new JobBuilder("feedRenew", jobRepository)
                .start(feedRenewStep())
                .build();
    }

    @Bean
    public Step feedRenewStep() {
        return new StepBuilder("feedLikeRenewStep", jobRepository)
                .<FeedRead, FeedRead>chunk(10, transactionManager)
                .reader(feedReader())
                .processor(feedProcessor())
                .writer(feedWriter())
                .listener(feedLoggingStepExecutionListener())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<FeedRead> feedReader() {
        return new IteratorItemReader<>(
                feedReadMongoTemplate.find(
                        new Query(),
                        FeedRead.class
                )
        );
    }


    @Bean
    public ItemProcessor<FeedRead, FeedRead> feedProcessor() {
        return feedRead -> {
            // 실제 좋아요 수
            long actualLikeCount = likesRepository
                    .countByKindAndKindUuidAndStatus("feed", feedRead.getFeedUuid(), true);
            // 실제 싫어요 수
            long actualDislikeCount = dislikesRepository
                    .countByKindAndKindUuidAndStatus("feed", feedRead.getFeedUuid(), true);

            long commentCount = feedCommentReadRepository
                    .countByFeedUuidAndIsDeletedFalse(feedRead.getFeedUuid());


            log.info("Processing feed: {}, Updated like count: {}, Updated dislike count: {}",
                    feedRead.getFeedUuid(), actualLikeCount, actualDislikeCount);

            return FeedRead.builder()
                    .id(feedRead.getId())
                    .feedUuid(feedRead.getFeedUuid())
                    .memberUuid(feedRead.getMemberUuid())
                    .title(feedRead.getTitle())
                    .content(feedRead.getContent())
                    .categoryName(feedRead.getCategoryName())
                    .visibility(feedRead.getVisibility())
                    .hashtags(feedRead.getHashtags())
                    .mediaList(feedRead.getMediaList())
                    .likeCount(actualLikeCount)
                    .dislikeCount(actualDislikeCount)
                    .netLikes(actualLikeCount - actualDislikeCount)
                    .commentCount(commentCount)
                    .createdAt(feedRead.getCreatedAt())
                    .updatedAt(feedRead.getUpdatedAt())
                    .build();
        };
    }

    @Bean
    public ItemWriter<FeedRead> feedWriter() {
        return items -> {
            for (FeedRead feedRead : items) {
                feedKafkaPublisher.send(FeedMetricsUpdateEvent.toDto(feedRead));
            }
        };
    }

    @Bean
    public StepExecutionListener feedLoggingStepExecutionListener() {
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
