package com.mulmeong.batchserver.feed.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.mulmeong.batchserver.feed.infrastructure.repository",
        mongoTemplateRef = "feedReadMongoTemplate"
)
public class FeedMongoConfig {

    @Value("${spring.data.mongodb.contest.dbname}")
    private String DB_NAME;

    @Value("${spring.data.mongodb.feed.uri}")
    private String feedMongoUri;

    @Bean(name = "feedReadMongoTemplate")
    public MongoTemplate contestMongoTemplate() {
        MongoClient mongoClient = MongoClients.create(feedMongoUri);
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient, DB_NAME);
        return new MongoTemplate(factory);
    }

}
