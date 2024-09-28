package com.madhav.newsapi.storage;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.madhav.newsapi.storage.entity.StoredNewsArticle;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository
public class StoredNewsArticleDAO {

    @Qualifier("DynamoDB_NewsLine")
    @Autowired
    private DynamoDBMapper mapper;

    public void createArticle(final StoredNewsArticle storedNewsArticle) throws Exception {
        final Map<String, ExpectedAttributeValue> newsArticleExpectedAttributeValue = new HashMap<>();
        final DynamoDBSaveExpression newsArticleSaveExpression = new DynamoDBSaveExpression()
                .withExpected(newsArticleExpectedAttributeValue);
        final String category = storedNewsArticle.getCategory();
        final String author = storedNewsArticle.getAuthor();
        try {
            log.info("Storing News Article with category {} to author {}", category, author);

            mapper.save(storedNewsArticle, newsArticleSaveExpression);

            log.info("Stored News Article with category {} to author {}", category, author);
        } catch (final ConditionalCheckFailedException ex) {
            String format = "Can't bind customer %s to vendor %s as it is already bound to another account";
            String msg = String.format(format, category, author);
            log.error(msg, ex);
            throw new Exception(msg, ex);
        }
    }

    public List<StoredNewsArticle> getAllNewsArticleByCategory(String category) throws Exception {

        log.info("Retrieving list of News Article by Category={}", category);

        StoredNewsArticle newsArticle = new StoredNewsArticle();
        newsArticle.setCategory(category);

        final DynamoDBQueryExpression<StoredNewsArticle> queryExpression = new DynamoDBQueryExpression<StoredNewsArticle>()
                .withHashKeyValues(newsArticle).withConsistentRead(false);
        try {
            return mapper.query(StoredNewsArticle.class, queryExpression);
        } catch (Exception e) {
            log.error("Exception while getting news articles {}", e);
            throw new Exception(e);
        }
    }

}
