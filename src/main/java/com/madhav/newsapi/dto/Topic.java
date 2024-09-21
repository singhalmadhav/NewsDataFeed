package com.madhav.newsapi.dto;

import com.madhav.newsapi.observer.Observer;
import com.madhav.newsapi.observer.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topic implements Subject {

    public String title;
    public List<Observer> observerList;
    public List<Article> articles;

    public void addArticle(Article article) {
        articles.add(article);
        notifyObservers(article);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
    }

    @Override
    public void subscribe(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(Article article){
        for(Observer observer: observerList) {
            observer.update(article);
        }
    }

}
