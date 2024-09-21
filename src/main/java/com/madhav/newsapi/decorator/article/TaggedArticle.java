package com.madhav.newsapi.decorator.article;

import com.madhav.newsapi.dto.Article;

import java.util.List;

public class TaggedArticle extends Article {

    private List<String> tags;

    private void addTags(String tag) {
        tags.add(tag);
    }
}
