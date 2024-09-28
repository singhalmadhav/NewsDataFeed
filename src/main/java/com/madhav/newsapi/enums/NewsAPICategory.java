package com.madhav.newsapi.enums;

public enum NewsAPICategory {
    BUSINESS("business"),
    SPORTS("sports"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    TECHNOLOGY("technology");

    private final String category;

    // NewsAPICategory
    NewsAPICategory(String category) {
        this.category = category;
    }
    // Getter
    public String getCategory() {
        return category;
    }

}
