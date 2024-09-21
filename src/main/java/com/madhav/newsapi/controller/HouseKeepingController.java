package com.madhav.newsapi.controller;

import com.madhav.newsapi.model.DataModel;
import com.madhav.newsapi.service.NewsAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/news/cron/")
public class HouseKeepingController {

    @Autowired
    NewsAPIService newsAPIService;

    @GetMapping("/get-headlines")
    public Flux<DataModel> getHeadlines() {
        try {
            return newsAPIService.getTrendingNews();
        } catch (Exception e) {
            e.printStackTrace();
            return Flux.error(new Exception("Server Error"));
        }
    }
}
