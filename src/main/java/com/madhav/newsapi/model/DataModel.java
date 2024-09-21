package com.madhav.newsapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DataModel {
    @JsonProperty("status")
    private String status;
    @JsonProperty("totalResults")
    private int totalResults;
    @JsonProperty("articles")
    private List<Article> articles;
}
