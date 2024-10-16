package com.nileshprajapati.incubator_demo.controller;

import com.nileshprajapati.incubator_demo.internal.apis.NewsApi;
import com.nileshprajapati.incubator_demo.internal.models.NewsSourcesResponse;
import com.nileshprajapati.incubator_demo.internal.models.TopNewsHeadlineResponse;
import com.nileshprajapati.incubator_demo.service.NewsService;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class NewsController implements NewsApi {

    private final NewsService newsService;

    @Autowired
    NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public ResponseEntity<TopNewsHeadlineResponse> topHeadlinesUsingCountryCode(String country) {
      try {
          if (country == null || country.isEmpty()) {
              throw new BadRequestException("Country code is mandatory");
          } else {
              Future<TopNewsHeadlineResponse> futureResult = this.newsService.getTopNewsHeadlines(country);
              return ResponseEntity.ofNullable(futureResult.get());
          }
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
    }

    @Override
    public ResponseEntity<NewsSourcesResponse> topNewsSources() {
        try {
            Future<NewsSourcesResponse> futureResult = this.newsService.getNewsSources();
            return ResponseEntity.ofNullable(futureResult.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
