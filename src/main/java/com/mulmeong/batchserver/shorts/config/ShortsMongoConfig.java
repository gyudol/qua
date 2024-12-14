package com.mulmeong.batchserver.shorts.config;

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
        basePackages = "com.mulmeong.batchserver.shorts.infrastructure.repository",
        mongoTemplateRef = "shortsReadMongoTemplate"
)
public class ShortsMongoConfig {

    @Value("${spring.data.mongodb.shorts.database}")
    private String dbName;

    @Value("${spring.data.mongodb.shorts.uri}")
    private String shortsMongoUri;

    @Bean(name = "shortsReadMongoTemplate")
    public MongoTemplate contestMongoTemplate() {
        MongoClient mongoClient = MongoClients.create(shortsMongoUri);
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient, dbName);
        return new MongoTemplate(factory);
    }

}
