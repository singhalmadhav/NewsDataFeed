package com.madhav.newsapi.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class NewsFeed {
    private List<Article> articles;
    private List<User> users;
    private List<Topic> topics;

    public NewsFeed() {
        this.articles = new ArrayList<>();
        this.users = new ArrayList<>();
        this.topics = new ArrayList<>();
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }

    public List<Article> getArticleByTopic(Topic topic) {
        return topic.getArticles();
    }

    public List<Article> getArticleByUser(User user) {
        List<Article> articleToUser = new ArrayList<>();
        List<Topic> subscribedTopics = user.getSubscribedTopics();
        for (Topic topic: subscribedTopics) {
            topic.getArticles().stream().map(
                    article -> articleToUser.add(article)
            );
        }
        return articleToUser;
    }

    public List<Article> getArticlesByTimePeriod(LocalDateTime start, LocalDateTime end) {
        List<Article> articlesByTime = new ArrayList<>();
        for (Article article : articles) {
            if (article.getLocalDateTime().isAfter(start) && article.getLocalDateTime().isBefore(end)) {
                articlesByTime.add(article);
            }
        }
        return articlesByTime;
    }


}
