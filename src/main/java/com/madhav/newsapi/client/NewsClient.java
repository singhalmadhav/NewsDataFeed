package com.madhav.newsapi.client;

import com.madhav.newsapi.model.DataModel;

public interface NewsClient {
    DataModel getTopTrendingNews();

    DataModel getHeadlinesByCategory(String category);

    DataModel getNewsArticleByQuery(String query);
}
