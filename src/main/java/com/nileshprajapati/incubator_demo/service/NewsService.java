package com.nileshprajapati.incubator_demo.service;

import com.nileshprajapati.incubator_demo.config.ApplicationConfiguration;
import com.nileshprajapati.incubator_demo.external.apis.NewsSourcesApi;
import com.nileshprajapati.incubator_demo.external.apis.NewsTopHeadlinesApi;
import com.nileshprajapati.incubator_demo.external.models.NewsArticleModel;
import com.nileshprajapati.incubator_demo.external.models.NewsSourcesModel;
import com.nileshprajapati.incubator_demo.external.models.NewsSourcesResponseModel;
import com.nileshprajapati.incubator_demo.external.models.TopHeadlineResponseModel;
import com.nileshprajapati.incubator_demo.internal.models.*;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    private final NewsSourcesApi newsSourcesApi;
    private final NewsTopHeadlinesApi newsTopHeadlinesApi;

    @Autowired
    NewsService(NewsSourcesApi newsSourcesApi, NewsTopHeadlinesApi newsTopHeadlinesApi) {
        this.newsSourcesApi = newsSourcesApi;
        this.newsTopHeadlinesApi = newsTopHeadlinesApi;
    }

    public ResponseEntity<TopNewsHeadlineResponse> getTopNewsHeadlines(String country) throws IOException {
        try {
            Call<TopHeadlineResponseModel> apiCall = this.newsTopHeadlinesApi.topHeadlines(country, ApplicationConfiguration.APICategory.news.apiKey());
            Response<TopHeadlineResponseModel> response = apiCall.execute();
            if (response.isSuccessful() && response.body() != null) {
                TopHeadlineResponseModel object =  response.body();
                return ResponseEntity.ok(mapToMicroserviceNewsTopHeadlinesModel(object));
            } else {
                throw new ResponseStatusException(HttpStatusCode.valueOf(response.code()), response.message());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<NewsSourcesResponse> getNewsSources() throws IOException {
       try {
           Call<NewsSourcesResponseModel> callApi = this.newsSourcesApi.newsSources(ApplicationConfiguration.APICategory.news.apiKey());
           Response<NewsSourcesResponseModel> responseModel = callApi.execute();
           if (responseModel.isSuccessful() && responseModel.body() != null) {
               NewsSourcesResponseModel newsSourcesResponseModel = responseModel.body();
               return ResponseEntity.ok(mapToMicroserviceNewsSourceModel(newsSourcesResponseModel));
           } else {
               throw new ResponseStatusException(HttpStatusCode.valueOf(responseModel.code()), responseModel.message());
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    private TopNewsHeadlineResponse mapToMicroserviceNewsTopHeadlinesModel(TopHeadlineResponseModel object) {
        TopNewsHeadlineResponse newsHeadlineResponse = new TopNewsHeadlineResponse();
        List<NewsArticle> articles = null;
        newsHeadlineResponse.setStatus(object.getStatus());
        newsHeadlineResponse.setTotalResults(object.getTotalResults());
        if (object.getArticles() != null) {
            articles = new ArrayList<>();
            for (NewsArticleModel articleModel : object.getArticles()) {
                NewsArticle newsArticle = getNewsArticle(articleModel);
                articles.add(newsArticle);
            }
            newsHeadlineResponse.setArticles(articles);
        }
        return newsHeadlineResponse;
    }

    private static @NotNull NewsArticle getNewsArticle(NewsArticleModel articleModel) {
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setTitle(articleModel.getTitle());
        newsArticle.setUrl(articleModel.getUrl());
        newsArticle.setDescription(articleModel.getDescription());
        newsArticle.setAuthor(articleModel.getAuthor());
        newsArticle.setContent(articleModel.getContent());

        NewsArticleSource newsArticleSource = new NewsArticleSource();
        newsArticleSource.setId(articleModel.getSource().getId());
        newsArticleSource.setName(articleModel.getSource().getName());
        newsArticle.setSource(newsArticleSource);

        newsArticle.setPublishedAt(articleModel.getPublishedAt());
        newsArticle.setUrlToImage(articleModel.getUrlToImage());
        return newsArticle;
    }

    private NewsSourcesResponse mapToMicroserviceNewsSourceModel(NewsSourcesResponseModel object) {
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

    private static @NotNull NewsSource getNewsSource(NewsSourcesModel newsSourcesModel) {
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
}
