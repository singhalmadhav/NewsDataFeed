package com.madhav.newsapi.dto;

import com.madhav.newsapi.observer.Observer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Observer {

    String name;
    String email;
    List<Topic> subscribedTopics;

    public void subscribeToTopic(Topic topic) {
        subscribedTopics.add(topic);
        topic.subscribe(this);
    }

    public void unsubscribeToTopic(Topic topic) {
        subscribedTopics.remove(topic);
        topic.unsubscribe(this);
    }

    @Override
    public void update(Article article) {
        System.out.println("New article on topic you're subscribed to: " + article.getTitle());
    }
}
