package com.nileshprajapati.incubator_demo.service;

import com.nileshprajapati.incubator_demo.external.models.NewsArticleModel;
import com.nileshprajapati.incubator_demo.external.models.TopHeadlineResponseModel;
import com.nileshprajapati.incubator_demo.external.apis.NewsTopHeadlinesApi;
import com.nileshprajapati.incubator_demo.internal.models.NewsArticle;
import com.nileshprajapati.incubator_demo.internal.models.NewsArticleSource;
import com.nileshprajapati.incubator_demo.internal.models.TopNewsHeadlineResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import retrofit2.Call;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService implements NewsTopHeadlinesApi {

    private final NewsTopHeadlinesApi topHeadlinesApi;

    @Autowired
    public NewsService(NewsTopHeadlinesApi topHeadlinesApi) {
        this.topHeadlinesApi = topHeadlinesApi;
    }

    public TopNewsHeadlineResponse mapToMicroserviceModel(TopHeadlineResponseModel object) {
        TopNewsHeadlineResponse response = new TopNewsHeadlineResponse();
        List<NewsArticle> articles = null;
        response.setStatus(object.getStatus());
        response.setTotalResults(object.getTotalResults());
        if (object.getArticles() != null) {
            articles = new ArrayList<>();
            for(NewsArticleModel articleModel: object.getArticles()) {
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
                articles.add(newsArticle);
            }
        }
        response.setArticles(articles);
        return response;
    }

    @Override
    public Call<TopHeadlineResponseModel> topHeadlines(String country, String apiKey) {
        return this.topHeadlinesApi.topHeadlines(country, apiKey);
    }
}
