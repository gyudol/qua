package com.mulmeong.batchserver.comment.config;

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
@EnableMongoRepositories(
        basePackages = "com.mulmeong.batchserver.comment.infrastructure.repository",
        mongoTemplateRef = "commentReadMongoTemplate"
)
@Slf4j
public class CommentMongoConfig {

    @Value("${spring.data.mongodb.comment.dbname}")
    private String DB_NAME;

    @Value("${spring.data.mongodb.comment.uri}")
    private String commentMongoUri;

    @Bean(name = "commentReadMongoTemplate")
    public MongoTemplate commentMongoTemplate() {
        log.info("1111 : " + DB_NAME);
        log.info("2222 : " + commentMongoUri);
        MongoClient mongoClient = MongoClients.create(commentMongoUri);
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient, DB_NAME);
        return new MongoTemplate(factory);
    }

}
