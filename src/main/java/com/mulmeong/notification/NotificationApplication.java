package com.mulmeong.notification;

import com.mulmeong.notification.client.common.FeignErrorDecoder;
import com.mulmeong.notification.client.member.MemberClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableMongoAuditing
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
