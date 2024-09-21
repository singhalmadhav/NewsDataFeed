package com.madhav.newsapi.observer;

import com.madhav.newsapi.dto.Article;

public interface Subject {

    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyObservers(Article article);

}
