package com.nileshprajapati.incubator_demo.thirdparty_interface;

import com.nileshprajapati.incubator_demo.model.GetCityResponseModel;

import io.swagger.v3.oas.annotations.Operation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface CityApi {

    @Operation(summary = "Get list of cities by country_id")
    @Headers({"x-rapidapi-key:770dcb8b99msh50cf902ac91b5b7p121690jsn90fc925d74d7",
            "Accept: application/json", "Content-Type: application/json",
            "x-rapidapi-host: wft-geo-db.p.rapidapi.com"})
    @GET(value = "/v1/geo/cities")
    Call<GetCityResponseModel> getCities(@Query("countryIds") String countryID, @Query("limit") int limit, @Query("offset") int offset);
}