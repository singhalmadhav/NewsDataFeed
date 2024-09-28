package com.madhav.newsapi.service.impl;

import com.madhav.newsapi.client.NewsClient;
import com.madhav.newsapi.storage.StoredNewsArticleDAO;
import com.madhav.newsapi.storage.entity.StoredNewsArticle;
import com.madhav.newsapi.model.Article;
import com.madhav.newsapi.model.DataModel;
import com.madhav.newsapi.repository.StoredNewsArticleRepository;
import com.madhav.newsapi.service.HousekeepingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class HousekeepingServiceImpl implements HousekeepingService {

    @Autowired
    @Qualifier("news-api")
    NewsClient newsClient;

    @Autowired
    StoredNewsArticleRepository storedNewsArticleRepository;

    @Autowired
    StoredNewsArticleDAO storedNewsArticleDAO;

    public void fetchArticlesByCategory(String category) throws Exception {
        DataModel dataModel = newsClient.getHeadlinesByCategory(category);
        int c = 0 ;
        for (Article article: dataModel.getArticles()) {
            StoredNewsArticle newsArticle = toStoredNewsArticle(article,category);
            try {
                storedNewsArticleDAO.createArticle(newsArticle);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            c++;
            if (c>=100) break;
        }
    }

    public List<StoredNewsArticle> getArticlesByCategory(String category) throws Exception {
        return storedNewsArticleDAO.getAllNewsArticleByCategory("business");
    }

    public StoredNewsArticle toStoredNewsArticle(Article article, String category) {
        return StoredNewsArticle.builder()
                .id(UUID.randomUUID().toString())
                .name(article.getSource().getName())
                .title(article.getTitle())
                .author(article.getAuthor())
                .url(article.getUrl())
                .date(article.getDate())
                .createdAt(LocalDateTime.now().toString())
                .description(article.getDescription())
                .content(article.getContent())
                .urlToImage(article.getUrlToImage())
                .category(category)
                .build();

    }
}
