package com.mulmeong.batchserver.feed.config;

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

@Configuration
@Slf4j
@EnableMongoRepositories(
        basePackages = "com.mulmeong.batchserver.feed.infrastructure.repository",
        mongoTemplateRef = "feedReadMongoTemplate"
)
public class FeedMongoConfig {

    @Value("${spring.data.mongodb.feed.database}")
    private String dbName;

    @Value("${spring.data.mongodb.feed.uri}")
    private String feedMongoUri;

    @Bean(name = "feedReadMongoTemplate")
    public MongoTemplate feedMongoTemplate() {
        ConnectionString connectionString = new ConnectionString(feedMongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        MongoClient mongoClient = MongoClients.create(mongoClientSettings);

        return new MongoTemplate(mongoClient, dbName);

    }

}
