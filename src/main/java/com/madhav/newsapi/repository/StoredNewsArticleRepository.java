package com.madhav.newsapi.repository;

import com.madhav.newsapi.model.Article;
import com.madhav.newsapi.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Map;

@Repository
public class StoredNewsArticleRepository {
    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "StoredArticle";

    @Autowired
    public StoredNewsArticleRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(Article article) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(Map.of(
                        "id", AttributeValue.builder().s(article.getSource().getId()).build(),
                        "name", AttributeValue.builder().s(article.getSource().getName()).build(),
                        "author",AttributeValue.builder().s(article.getAuthor()).build(),
                        "title",AttributeValue.builder().s(article.getTitle()).build(),
                        "description",AttributeValue.builder().s(article.getDescription()).build(),
                        "url",AttributeValue.builder().s(article.getUrl()).build(),
                        "urlToImage",AttributeValue.builder().s(article.getUrlToImage()).build(),
                        "date",AttributeValue.builder().s(article.getDate()).build(),
                        "content",AttributeValue.builder().s(article.getContent()).build()
                ))
                .build();
        dynamoDbClient.putItem(request);
    }

    public Article findById(String id) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("id", AttributeValue.builder().s(id).build()))
                .build();
        GetItemResponse response = dynamoDbClient.getItem(request);

        if (response.hasItem()) {
            Map<String, AttributeValue> item = response.item();
            Article result = new Article();
            Source source = new Source();
            source.setId(item.get("id").s());
            source.setName(item.get("name").s());
            result.setSource(source);
            return result;
        }
        return null; // Or throw an exception if preferred
    }
}
