package com.nileshprajapati.incubator_demo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.nileshprajapati.incubator_demo.config.CityApiConfiguration;
import com.nileshprajapati.incubator_demo.constant.CityApiConstant;
import com.nileshprajapati.incubator_demo.model.City;
import com.nileshprajapati.incubator_demo.model.GetCityResponseModel;
import com.nileshprajapati.incubator_demo.thirdparty_interface.CityApi;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@Service
public class CityService {

    private final Gson gson;
    private Retrofit retrofit;
    private CityApi cityApi;

    public CityService(Gson gson) {
        retrofit = CityApiConfiguration.getRetrofit();
        cityApi = retrofit.create(CityApi.class);
        this.gson = gson;
    }

    public List<City> getAllCities(String countryID, int limit, int offset) {
        Call<GetCityResponseModel> apiCall = cityApi.getCities(countryID, limit, offset);
        try {
           Response<GetCityResponseModel> response = apiCall.execute();
           System.out.println(response.body());
           if (response.isSuccessful() && response.body() != null) {
               GetCityResponseModel object = response.body();
                return object.getCities();
           } else {
               return Collections.emptyList();
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
