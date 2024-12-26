package com.mulmeong.contest.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class GoogleVisionConfig {

    @Bean
    public ImageAnnotatorClient imageAnnotatorClient() throws IOException {

        GoogleCredentials credentials = GoogleCredentials.fromStream(
                new ClassPathResource("google-credentials.json").getInputStream());

        ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();

        return ImageAnnotatorClient.create(settings);
    }
}
