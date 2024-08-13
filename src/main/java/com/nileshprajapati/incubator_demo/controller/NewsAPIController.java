package com.nileshprajapati.incubator_demo.controller;

import com.nileshprajapati.incubator_demo.codegen.models.TopHeadlineResponse;
import com.nileshprajapati.incubator_demo.config.ApplicationConfiguration;
import com.nileshprajapati.incubator_demo.model.NewsTopHeadlineResponse;
import com.nileshprajapati.incubator_demo.service.NewsService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import retrofit2.Call;
import retrofit2.Response;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.rmi.RemoteException;

@RestController
@RequestMapping(path = "/api/v1/news")
public class NewsAPIController {

    private NewsService newsService;

    @Autowired
    NewsAPIController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping(path = "/top-headlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewsTopHeadlineResponse> getTopHeadlines(@NotNull @RequestParam String countryCode) throws IOException {
        if (countryCode == null || countryCode.isEmpty()) {
            throw new BadRequestException("Country code is mandatory");
        } else {
           try {
               Call<TopHeadlineResponse> apiCall = this.newsService.topHeadlinesByCountry(countryCode, ApplicationConfiguration.APICategory.news.apiKey());
                Response<TopHeadlineResponse> response = apiCall.execute();
                if (response.isSuccessful() && response.body() != null) {
                    TopHeadlineResponse object =  response.body();
                    return ResponseEntity.ok(this.newsService.map(object));
                } else {
                    throw new ResponseStatusException(HttpStatusCode.valueOf(response.code()), response.message());
                }
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
        }
    }
}
