package com.nileshprajapati.incubator_demo.service;

import com.nileshprajapati.incubator_demo.model.City;
import com.nileshprajapati.incubator_demo.model.GetCityResponseModel;
import com.nileshprajapati.incubator_demo.interfaces.CityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Service
public class CityService {

    private CityApi cityApi;

    @Autowired
    public CityService(CityApi cityApi) {
        this.cityApi = cityApi;
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
               throw new ResponseStatusException(HttpStatusCode.valueOf(response.code()), response.message());
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
