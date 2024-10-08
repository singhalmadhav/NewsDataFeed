package com.madhav.newsapi.storage.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@DynamoDBTable(tableName = "StoredNewsArticle")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoredNewsArticle {

    @DynamoDBHashKey(attributeName = "ArticleId")
    private String id;

    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    private String createdAt;

    @DynamoDBIndexHashKey(attributeName = "category", globalSecondaryIndexName = "category_index")
    private String category;

    @DynamoDBVersionAttribute(attributeName = "Version")
    private Long version;

    @DynamoDBAttribute(attributeName = "Name")
    private String name;

    @DynamoDBAttribute(attributeName = "Author")
    private String author;

    @DynamoDBAttribute(attributeName = "Title")
    private String title;

    @DynamoDBAttribute(attributeName = "Description")
    private String description;

    @DynamoDBAttribute(attributeName = "Article_Url")
    private String url;

    @DynamoDBAttribute(attributeName = "Article_Image_URL")
    private String urlToImage;

    @DynamoDBAttribute(attributeName = "date")
    private String date;

    @DynamoDBAttribute(attributeName = "content")
    private String content;



}
