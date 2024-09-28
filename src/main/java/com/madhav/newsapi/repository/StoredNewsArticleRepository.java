package com.madhav.newsapi.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.madhav.newsapi.storage.entity.StoredNewsArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StoredNewsArticleRepository {
    private final String tableName = "StoredArticle";

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public StoredNewsArticleRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void save(StoredNewsArticle storedNewsArticle) {
        dynamoDBMapper.save(storedNewsArticle);
    }

//    public PutItemRequest createRequest(Article article, String category) {
//        PutItemRequest request = PutItemRequest.builder()
//                .tableName(tableName)
//                .item(Map.of(
//                        "id", AttributeValue.builder().s(article.getSource().getId()).build(),
//                        "name", AttributeValue.builder().s(article.getSource().getName()).build(),
//                        "category",AttributeValue.builder().s(category).build(),
//                        "title",AttributeValue.builder().s(article.getTitle()).build(),
//                        "description",AttributeValue.builder().s(article.getDescription()).build(),
//                        "url",AttributeValue.builder().s(article.getUrl()).build(),
//                        "urlToImage",AttributeValue.builder().s(article.getUrlToImage()).build(),
//                        "date",AttributeValue.builder().s(article.getDate()).build(),
//                        "content",AttributeValue.builder().s(article.getContent()).build(),
//                        "creationDate", AttributeValue.builder().s(LocalDateTime.now().toString()).build()
//                ))
//                .build();
//        return request;
//    }

    public List<StoredNewsArticle> findByCategory(String category) {
        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#category", "category");

        // Create a query expression
        DynamoDBQueryExpression<StoredNewsArticle> queryExpression = new DynamoDBQueryExpression<StoredNewsArticle>()
                .withKeyConditionExpression("#category = :category")
                .withExpressionAttributeNames(expressionAttributeNames)
                .withExpressionAttributeValues(Map.of(":category", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS(category)));

        // Execute the query
        PaginatedQueryList<StoredNewsArticle> result = dynamoDBMapper.query(StoredNewsArticle.class, queryExpression);
        return result;
    }

}
