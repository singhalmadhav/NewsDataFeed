package com.madhav.newsapi.service;

import com.madhav.newsapi.model.DataModel;
import reactor.core.publisher.Flux;

public interface NewsAPIService {

    Flux<DataModel> getTrendingNews();

    Flux<DataModel> getTrendingNewsByCategory(String category);
}
