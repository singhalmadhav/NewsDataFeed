package com.madhav.newsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class NewsArticle extends Article {
    public NewsArticle(String title,
                       String author,
                       String content,
                       LocalDateTime localDateTime,
                       List<Topic> topics) {
        super(title,author,content,localDateTime,topics);
    }
}
