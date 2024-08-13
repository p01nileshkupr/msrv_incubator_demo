package com.nileshprajapati.incubator_demo.service;

import com.nileshprajapati.incubator_demo.model.NewsTopHeadlineResponse;
import com.nileshprajapati.incubator_demo.codegen.api.TopHeadlinesApi;
import com.nileshprajapati.incubator_demo.codegen.models.TopHeadlineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;

@Service
public class NewsService implements TopHeadlinesApi {

    private final TopHeadlinesApi topHeadlinesApi;

    @Autowired
    public NewsService(TopHeadlinesApi topHeadlinesApi) {
        this.topHeadlinesApi = topHeadlinesApi;
    }

    public NewsTopHeadlineResponse map(TopHeadlineResponse object) {
        NewsTopHeadlineResponse response = new NewsTopHeadlineResponse();
        response.setStatus(object.getStatus());
        response.setTotalResults(object.getTotalResults());
        response.setArticles(object.getArticles());
        return response;
    }

    @Override
    public Call<TopHeadlineResponse> topHeadlinesByCountry(String country, String apiKey) {
       return this.topHeadlinesApi.topHeadlinesByCountry(country, apiKey);
    }
}
