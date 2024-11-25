package com.mulmeong.feed.read;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FeedReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedReadApplication.class, args);
    }

}
