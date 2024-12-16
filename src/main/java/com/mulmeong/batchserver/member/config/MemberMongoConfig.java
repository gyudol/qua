package com.mulmeong.batchserver.member.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Objects;

@Configuration
@Slf4j
@EnableMongoRepositories(
        basePackages = "com.mulmeong.batchserver.member.infrastructure.repository",
        mongoTemplateRef = "memberReadMongoTemplate"
)
public class MemberMongoConfig {
    @Value("${spring.data.mongodb.member.uri}")
    private String memberMongoUri;

    @Bean(name = "memberReadMongoTemplate")
    public MongoTemplate memberMongoTemplate() {
        ConnectionString connectionString = new ConnectionString(memberMongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        MongoClient mongoClient = MongoClients.create(mongoClientSettings);

        return new MongoTemplate(mongoClient, Objects.requireNonNull(connectionString.getDatabase()));

    }
}
