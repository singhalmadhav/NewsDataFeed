package com.madhav.newsapi.controller;

import com.madhav.newsapi.enums.NewsAPICategory;
import com.madhav.newsapi.service.HousekeepingService;
import com.madhav.newsapi.storage.entity.StoredNewsArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news/cron/")
public class HouseKeepingController {


    @Autowired
    HousekeepingService housekeepingService;

    @PostMapping("/storeArticles")
    public void getHeadlines() throws Exception {
        try {
            housekeepingService.fetchArticlesByCategory(NewsAPICategory.BUSINESS.getCategory());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Server Error");
        }
    }

    @GetMapping("/getArticles")
    public ResponseEntity getNewsHeadlines() throws Exception {
        try {
            List<StoredNewsArticle> article = housekeepingService.getArticlesByCategory(NewsAPICategory.BUSINESS.getCategory());
            return ResponseEntity.of(Optional.ofNullable(article));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Server Error");
        }
    }
}
