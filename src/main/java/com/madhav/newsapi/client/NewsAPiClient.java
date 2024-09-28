package com.madhav.newsapi.client;

import com.madhav.newsapi.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Slf4j
@Configuration("news-api")
public class NewsAPiClient implements NewsClient {

    @Autowired
    WebClient webClient;

    public static String API_KEY = "64a239894d104dc49a6c1674b2c1b2c2";

    public static String BASE_URL = "https://newsapi.org/v2";
    public static String HEADLINES_URL = BASE_URL + "/top-headlines";
    public static String QUERY_NEWS_URL = "https://newsapi.org/v2/everything?q={q}country=us&apiKey=" + API_KEY;

    public DataModel getTopTrendingNews() {
        return invokeClient(getUrl("", "", ""));
    }

    public DataModel getHeadlinesByCategory(String category) {
        return invokeClient(getUrl("","us", category));
    }

    public DataModel getNewsArticleByQuery(String query) {
        return invokeClient(getUrl(query,"in", ""));
    }

    public DataModel invokeClient(String url) {
        log.info("Url to hit is {}", url);
        Mono<ResponseEntity<DataModel>> dataModelFlux =  webClient.get()
                .uri(url)
                .retrieve()
                .toEntity(DataModel.class)
                .doOnError(throwable -> log.error("Failed for Some Reason"));
        return dataModelFlux.block().getBody();
    }

    public String getUrl(String query, String country, String category) {
        String url = HEADLINES_URL;
        boolean hasParameter = false;
        if (!Objects.equals(query, "")) {
            url = url + (hasParameter ? "&" : "?") + "q=" + query;
            hasParameter = true;
        }
        if (!Objects.equals(country, "")) {
            url = url + (hasParameter ? "&" : "?") + "country=" + country;
            hasParameter = true;
        }
        if (!Objects.equals(category, "")) {
            url = url + (hasParameter ? "&" : "?") + "category=" + category;
            hasParameter = true;
        }
        url = url + (hasParameter ? "&" : "?") + "apiKey=" + API_KEY;
        return url;
    }

}
