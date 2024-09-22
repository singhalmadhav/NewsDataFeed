package com.madhav.newsapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DynamoDBConfig {

    @Bean
    public DynamoDbClient dynamoDbClient() throws URISyntaxException {
        return DynamoDbClient.builder()
                .region(Region.of("us-east-1"))
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
    }
}
