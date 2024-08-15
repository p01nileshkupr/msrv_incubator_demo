package com.nileshprajapati.incubator_demo.controller;

import com.nileshprajapati.incubator_demo.internal.apis.TopNewsSourcesApi;
import com.nileshprajapati.incubator_demo.internal.models.NewsSourcesResponse;
import com.nileshprajapati.incubator_demo.service.NewsSourcesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class NewsSourcesController implements TopNewsSourcesApi {

    private final NewsSourcesService newsSourcesService;

    @Autowired
    NewsSourcesController(NewsSourcesService newsSourcesService) {
        this.newsSourcesService = newsSourcesService;
    }

    @Override
    public ResponseEntity<NewsSourcesResponse> topNewsSources() {
        try {
            return this.newsSourcesService.getNewsSources();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
