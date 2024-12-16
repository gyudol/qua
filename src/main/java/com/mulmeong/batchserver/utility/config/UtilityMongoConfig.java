package com.mulmeong.batchserver.utility.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Objects;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.mulmeong.batchserver.utility.infrastructure.repository",
        mongoTemplateRef = "utilityReadMongoTemplate"
)
public class UtilityMongoConfig {

    @Value("${spring.data.mongodb.utility.uri}")
    private String utilityMongoUri;

    @Bean(name = "utilityReadMongoTemplate")
    public MongoTemplate utilityMongoTemplate() {
        ConnectionString connectionString = new ConnectionString(utilityMongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        MongoClient mongoClient = MongoClients.create(mongoClientSettings);

        return new MongoTemplate(mongoClient, Objects.requireNonNull(connectionString.getDatabase()));

    }

}
