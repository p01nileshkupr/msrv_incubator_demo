package com.nileshprajapati.incubator_demo.model;


import com.nileshprajapati.incubator_demo.codegen.models.NewsArticle;
import lombok.Data;

import java.util.List;

@Data
public class NewsTopHeadlineResponse {

    private String status = null;
    private Integer totalResults = null;
    private List<NewsArticle> articles = null;
}
