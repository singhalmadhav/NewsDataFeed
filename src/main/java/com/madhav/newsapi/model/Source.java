package com.madhav.newsapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Source {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
}
