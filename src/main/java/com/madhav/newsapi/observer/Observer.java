package com.madhav.newsapi.observer;

import com.madhav.newsapi.dto.Article;

public interface Observer {

    void update(Article article);
}
