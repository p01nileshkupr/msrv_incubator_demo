package com.nileshprajapati.incubator_demo.service;

import com.nileshprajapati.incubator_demo.config.CityApiConfiguration;
import com.nileshprajapati.incubator_demo.model.City;
import com.nileshprajapati.incubator_demo.model.GetCityResponseModel;
import com.nileshprajapati.incubator_demo.thirdparty_interface.CityApi;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class CityService {

    private Retrofit retrofit;
    private CityApi cityApi;

    public CityService() {
        retrofit = CityApiConfiguration.getRetrofit();
        cityApi = retrofit.create(CityApi.class);
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
