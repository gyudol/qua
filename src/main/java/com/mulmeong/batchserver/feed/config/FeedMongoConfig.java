package com.mulmeong.batchserver.feed.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Slf4j
@EnableMongoRepositories(
        basePackages = "com.mulmeong.batchserver.feed.infrastructure.repository",
        mongoTemplateRef = "feedReadMongoTemplate"
)
public class FeedMongoConfig {

    @Value("${spring.data.mongodb.feed.dbname}")
    private String dbName;

    @Value("${spring.data.mongodb.feed.uri}")
    private String feedMongoUri;

    @Bean(name = "feedReadMongoTemplate")
    public MongoTemplate feedMongoTemplate() {
        MongoClient mongoClient = MongoClients.create(feedMongoUri);
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient, dbName);
        return new MongoTemplate(factory);
    }

}
