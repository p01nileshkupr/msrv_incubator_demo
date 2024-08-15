package com.nileshprajapati.incubator_demo.service;

import com.nileshprajapati.incubator_demo.config.ApplicationConfiguration;
import com.nileshprajapati.incubator_demo.external.apis.NewsSourcesApi;
import com.nileshprajapati.incubator_demo.external.models.NewsSourcesModel;
import com.nileshprajapati.incubator_demo.external.models.NewsSourcesResponseModel;
import com.nileshprajapati.incubator_demo.internal.models.NewsSource;
import com.nileshprajapati.incubator_demo.internal.models.NewsSourcesResponse;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsSourcesService {

    private final NewsSourcesApi newsSourcesApi;

    @Autowired
    NewsSourcesService(NewsSourcesApi newsSourcesApi) {
        this.newsSourcesApi = newsSourcesApi;
    }

    private NewsSourcesResponse mapToMicroserviceModel(NewsSourcesResponseModel object) {
        NewsSourcesResponse newsSourcesResponse = new NewsSourcesResponse();
        newsSourcesResponse.setStatus(object.getStatus());
        List<NewsSource> newsSources = null;
        if (object.getSources() != null) {
            newsSources = new ArrayList<>();
            for (NewsSourcesModel newsSourcesModel : object.getSources()) {
                NewsSource newsSource = getNewsSource(newsSourcesModel);
                newsSources.add(newsSource);
            }
        }
        newsSourcesResponse.setSources(newsSources);
        return newsSourcesResponse;
    }

    private @NotNull NewsSource getNewsSource(NewsSourcesModel newsSourcesModel) {
        NewsSource newsSource = new NewsSource();
        newsSource.setId(newsSourcesModel.getId());
        newsSource.setName(newsSourcesModel.getName());
        newsSource.setUrl(newsSourcesModel.getUrl());
        newsSource.setDescription(newsSourcesModel.getDescription());
        newsSource.setCategory(newsSourcesModel.getCategory());
        newsSource.setCountry(newsSourcesModel.getCountry());
        newsSource.setLanguage(newsSourcesModel.getLanguage());
        return newsSource;
    }

    public ResponseEntity<NewsSourcesResponse> getNewsSources() throws IOException {
        Call<NewsSourcesResponseModel> callApi =  this.newsSourcesApi.newsSources(ApplicationConfiguration.APICategory.news.apiKey());
        Response<NewsSourcesResponseModel> responseModel = callApi.execute();
        if (responseModel.isSuccessful() && responseModel.body() != null) {
           NewsSourcesResponseModel newsSourcesResponseModel = responseModel.body();
            return ResponseEntity.ok(mapToMicroserviceModel(newsSourcesResponseModel));
        } else {
            throw new IOException(responseModel.errorBody().string());
        }
    }
}
