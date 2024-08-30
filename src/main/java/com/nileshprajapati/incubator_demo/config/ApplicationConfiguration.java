package com.nileshprajapati.incubator_demo.config;

import com.nileshprajapati.incubator_demo.external.apis.NewsSourcesApi;
import com.nileshprajapati.incubator_demo.external.apis.NewsTopHeadlinesApi;
import com.nileshprajapati.incubator_demo.interfaces.CityApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class ApplicationConfiguration {

    private final ExternalAPIConfigurationProperties externalAPIConfigurationProperties;

    @Autowired
    ApplicationConfiguration(ExternalAPIConfigurationProperties externalAPIConfigurationProperties) {
        this.externalAPIConfigurationProperties = externalAPIConfigurationProperties;
    }

    public String getCityAPIKey() {
        return externalAPIConfigurationProperties.getCityKey();
    }

    @Bean
    public NewsTopHeadlinesApi topHeadlinesApi() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(this.externalAPIConfigurationProperties.getNewsUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return build.create(NewsTopHeadlinesApi.class);
    }

    @Bean
    public CityApi cityApi() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(this.externalAPIConfigurationProperties.getCityUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return build.create(CityApi.class);
    }

    @Bean
    public NewsSourcesApi newsSourcesApi() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(this.externalAPIConfigurationProperties.getNewsUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return build.create(NewsSourcesApi.class);
    }
}

