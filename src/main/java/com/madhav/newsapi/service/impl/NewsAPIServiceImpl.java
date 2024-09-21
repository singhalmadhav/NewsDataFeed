package com.madhav.newsapi.service.impl;

import com.madhav.newsapi.model.DataModel;
import com.madhav.newsapi.service.NewsAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class NewsAPIServiceImpl implements NewsAPIService {

    @Autowired
    WebClient webClient;

    public Flux<DataModel> getTrendingNews() {
        return webClient.get()
                .uri("https://newsapi.org/v2/top-headlines?country=us&apiKey=64a239894d104dc49a6c1674b2c1b2c2")
                .retrieve()
                .bodyToFlux(DataModel.class)
                .doOnError(throwable -> log.error("Failed for Some Reason"));

    }

    public Flux<DataModel> getTrendingNewsByCategory(String category) {
        return webClient.get()
                .uri("https://newsapi.org/v2/top-headlines?country=us&apiKey=64a239894d104dc49a6c1674b2c1b2c2")
                .retrieve()
                .bodyToFlux(DataModel.class)
                .doOnError(throwable -> log.error("Failed for Some Reason"));
    }



}
