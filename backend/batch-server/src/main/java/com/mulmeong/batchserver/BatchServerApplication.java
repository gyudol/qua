package com.mulmeong.batchserver;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
public class BatchServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchServerApplication.class, args);
    }

}
