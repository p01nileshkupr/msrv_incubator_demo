package com.nileshprajapati.incubator_demo.service;

import com.nileshprajapati.incubator_demo.model.City;
import com.nileshprajapati.incubator_demo.model.GetCityResponseModel;
import com.nileshprajapati.incubator_demo.interfaces.CityApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class CityService {

    private CityApi cityApi;

    @Autowired
    public CityService(CityApi cityApi) {
        this.cityApi = cityApi;
    }

    @Async
    @Cacheable(value = "City-Cache", key = "#countryID + #limit + #offset")
    public Future<List<City>> getAllCities(String countryID, int limit, int offset) throws Exception {
        Call<GetCityResponseModel> apiCall = cityApi.getCities(countryID, limit, offset);
        try {
           Response<GetCityResponseModel> response = apiCall.execute();
           System.out.println(response.body());
           if (response.isSuccessful() && response.body() != null) {
               GetCityResponseModel object = response.body();
                return new AsyncResult<List<City>>(object.getCities());
           } else {
               throw new ResponseStatusException(HttpStatusCode.valueOf(response.code()), response.message());
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
