package com.mulmeong.batchserver.utility.config;

import com.mulmeong.batchserver.member.domain.document.MemberRead;
import com.mulmeong.batchserver.member.infrastructure.repository.MemberReadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
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
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@Slf4j
public class UtilityBatchConfig {

    private final PlatformTransactionManager transactionManager;
    private final MemberReadRepository memberReadRepository;
    private final MongoTemplate utilityMongoTemplate;
    private final MongoTemplate memberMongoTemplate;
    private final MongoTemplate feedMongoTemplate;
    private final MongoTemplate shortsMongoTemplate;
    private final JobRepository jobRepository;

    public UtilityBatchConfig(
            PlatformTransactionManager transactionManager,
            MemberReadRepository memberReadRepository,
            @Qualifier("utilityReadMongoTemplate") MongoTemplate utilityMongoTemplate,
            @Qualifier("memberReadMongoTemplate") MongoTemplate memberMongoTemplate,
            @Qualifier("feedReadMongoTemplate") MongoTemplate feedMongoTemplate,
            @Qualifier("shortsReadMongoTemplate") MongoTemplate shortsMongoTemplate,
            JobRepository jobRepository
    ) {
        this.transactionManager = transactionManager;
        this.memberReadRepository = memberReadRepository;
        this.utilityMongoTemplate = utilityMongoTemplate;
        this.memberMongoTemplate = memberMongoTemplate;
        this.feedMongoTemplate = feedMongoTemplate;
        this.shortsMongoTemplate = shortsMongoTemplate;
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job memberRenewJob() {
        return new JobBuilder("memberRenew", jobRepository)
                .start(memberRenewStep())
                .build();
    }

    @Bean
    public Step memberRenewStep() {
        return new StepBuilder("memberRenewStep", jobRepository)
                .<MemberRead, MemberRead>chunk(10, transactionManager)
                .reader(memberReader())
                .processor(memberProcessor())
                .writer(memberWriter())
                .listener(memberLoggingStepExecutionListener())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<MemberRead> memberReader() {
        return new IteratorItemReader<>(
                memberMongoTemplate.find(
                        new Query(),
                        MemberRead.class
                )
        );
    }

    @Bean
    public ItemProcessor<MemberRead, MemberRead> memberProcessor() {
        return memberRead -> {

            String memberUuid = memberRead.getMemberUuid();

            long followerCount = utilityMongoTemplate.count(
                    Query.query(Criteria.where("targetUuid").is(memberUuid)),
                    "follow"
            );

            long followingCount = utilityMongoTemplate.count(
                    Query.query(Criteria.where("sourceUuid").is(memberUuid)),
                    "follow"
            );

            long feedCount = feedMongoTemplate.count(
                    Query.query(Criteria.where("memberUuid").is(memberUuid)),
                    "feed"
            );

            long shortsCount = shortsMongoTemplate.count(
                    Query.query(Criteria.where("memberUuid").is(memberUuid)),
                    "shorts"
            );

            return MemberRead.builder()
                    .memberUuid(memberRead.getMemberUuid())
                    .nickname(memberRead.getNickname())
                    .profileImageUrl(memberRead.getProfileImageUrl())
                    .createdAt(memberRead.getCreatedAt())
                    .gradeId(memberRead.getGradeId())
                    .equippedBadgeId(memberRead.getEquippedBadgeId())
                    .followerCount((int) followerCount)
                    .followingCount((int) followingCount)
                    .feedCount((int) feedCount)
                    .shortsCount((int) shortsCount)
                    .build();
        };
    }

    @Bean
    public ItemWriter<MemberRead> memberWriter() {
        return memberReadRepository::saveAll;
    }

    @Bean
    public StepExecutionListener memberLoggingStepExecutionListener() {
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
