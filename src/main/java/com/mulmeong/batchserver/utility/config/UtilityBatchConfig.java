package com.mulmeong.batchserver.utility.config;

import com.mulmeong.batchserver.member.domain.document.MemberRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
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
import org.springframework.data.mongodb.core.BulkOperations;
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
@Slf4j
public class UtilityBatchConfig {

    private final PlatformTransactionManager transactionManager;
    @Qualifier("utilityReadMongoTemplate")
    private final MongoTemplate utilityMongoTemplate;
    @Qualifier("memberReadMongoTemplate")
    private final MongoTemplate memberMongoTemplate;
    private final JobRepository jobRepository;
    private final JobLauncher jobLauncher;

    @Scheduled(fixedRate = 3000000)
    public void runMemberRenewJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        log.info("Running job {}", jobParameters);

        jobLauncher.run(memberRenewJob(), jobParameters);
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

            long feedCount = utilityMongoTemplate.count(
                    Query.query(Criteria.where("memberUuid").is(memberUuid)),
                    "feed"
            );

            long shortsCount = utilityMongoTemplate.count(
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
        return items -> memberMongoTemplate.bulkOps(
                BulkOperations.BulkMode.UNORDERED,
                MemberRead.class,
                "member"
        ).insert(items).execute();
    }
}
