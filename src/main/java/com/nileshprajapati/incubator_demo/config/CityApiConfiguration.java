package com.nileshprajapati.incubator_demo.config;

import com.nileshprajapati.incubator_demo.constant.CityApiConstant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityApiConfiguration {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(CityApiConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
