package com.madhav.newsapi.controller;

import com.madhav.newsapi.model.DataModel;
import com.madhav.newsapi.service.NewsAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/news-feed")
public class NewsAPIController {

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

    @GetMapping("/get-headlines/{category}")
    public Flux<DataModel> getHeadlinesByCategory(@PathVariable("category") String category) {
        try {
            return newsAPIService.getTrendingNewsByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
            return Flux.error(new Exception("Server Error"));
        }
    }

//    @GetMapping("/get-headlines2")
//    public void getHeadlines2() {
//        try {
//            HttpRequest request = HttpRequest.ge
//        }
//    }
}
