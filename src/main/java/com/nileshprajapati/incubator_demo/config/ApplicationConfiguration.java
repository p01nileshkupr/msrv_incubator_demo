package com.nileshprajapati.incubator_demo.config;

import com.nileshprajapati.incubator_demo.external.apis.NewsSourcesApi;
import com.nileshprajapati.incubator_demo.external.apis.NewsTopHeadlinesApi;
import com.nileshprajapati.incubator_demo.interfaces.CityApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public NewsTopHeadlinesApi topHeadlinesApi() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(APICategory.news.baseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return build.create(NewsTopHeadlinesApi.class);
    }

    @Bean
    public CityApi cityApi() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(APICategory.city.baseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return build.create(CityApi.class);
    }

    @Bean
    public NewsSourcesApi newsSourcesApi() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(APICategory.news.baseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return build.create(NewsSourcesApi.class);
    }

    public enum APICategory {
        city, news;

        public String baseURL() {
            switch (this) {
                case news:
                    return "https://newsapi.org/v2/";
                case city:
                    return "https://wft-geo-db.p.rapidapi.com/";
                default:
                    return "";
            }
        }

        public String apiKey() {
            switch (this) {
                case news:
                    return "db599c5719684e41bb5b225b2a91b4a4";
                case city:
                    return "770dcb8b99msh50cf902ac91b5b7p121690jsn90fc925d74d7";
                default:
                    return "";
            }
        }
    }
}
