package com.mulmeong.batchserver.contest.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Objects;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.mulmeong.batchserver.contest.infrastructure.repository.mongo",
        mongoTemplateRef = "contestReadMongoTemplate"
)
public class ContestMongoConfig {

    @Value("${spring.data.mongodb.contest.uri}")
    private String contestMongoUri;

    @Bean(name = "contestReadMongoTemplate")
    public MongoTemplate contestReadMongoTemplate() {
        ConnectionString connectionString = new ConnectionString(contestMongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        MongoClient mongoClient = MongoClients.create(mongoClientSettings);

        return new MongoTemplate(mongoClient, Objects.requireNonNull(connectionString.getDatabase()));
    }

    @Bean
    public GridFsTemplate gridFsTemplate(
            @Qualifier("contestReadMongoTemplate") MongoTemplate mongoTemplate) throws Exception {
        return new GridFsTemplate(mongoTemplate.getMongoDatabaseFactory(), mongoTemplate.getConverter());
    }

}
