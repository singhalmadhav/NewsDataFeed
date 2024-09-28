package com.madhav.newsapi.service;

import com.madhav.newsapi.storage.entity.StoredNewsArticle;

import java.util.List;

public interface HousekeepingService {
    void fetchArticlesByCategory(String category) throws Exception;

    List<StoredNewsArticle> getArticlesByCategory(String category) throws Exception;
}
