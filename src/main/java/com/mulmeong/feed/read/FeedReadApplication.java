package com.mulmeong.feed.read;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class FeedReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedReadApplication.class, args);
    }

}
