package com.nileshprajapati.incubator_demo.controller;

import com.nileshprajapati.incubator_demo.config.ApplicationConfiguration;
import com.nileshprajapati.incubator_demo.external.apis.NewsTopHeadlinesApi;
import com.nileshprajapati.incubator_demo.external.models.TopHeadlineResponseModel;
import com.nileshprajapati.incubator_demo.internal.apis.NewsApi;
import com.nileshprajapati.incubator_demo.internal.models.NewsSourcesResponse;
import com.nileshprajapati.incubator_demo.internal.models.TopNewsHeadlineResponse;
import com.nileshprajapati.incubator_demo.service.NewsService;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

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
             return this.newsService.getTopNewsHeadlines(country);
          }
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
    }

    @Override
    public ResponseEntity<NewsSourcesResponse> topNewsSources() {
        try {
            return this.newsService.getNewsSources();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}