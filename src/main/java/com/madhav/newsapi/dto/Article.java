package com.madhav.newsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String title;
    private String description;
    private String author;
    private LocalDateTime localDateTime;
    private List<Topic> topicList;
}
