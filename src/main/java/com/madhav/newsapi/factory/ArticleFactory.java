package com.madhav.newsapi.factory;

import com.madhav.newsapi.dto.Article;
import com.madhav.newsapi.dto.BlogPosts;
import com.madhav.newsapi.dto.NewsArticle;
import com.madhav.newsapi.dto.Topic;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleFactory {
    public static Article createArticle(String type,
                                        String title,
                                        String author,
                                        String content,
                                        List<Topic> topics) {
        switch(type.toLowerCase()) {
            case "news":
                return new NewsArticle(title, author,content, LocalDateTime.now(), topics);
            case "blog":
                return new BlogPosts(title, author,content, LocalDateTime.now(), topics);
            default:
                throw new IllegalArgumentException("Invalid article type");
        }
    }
}
