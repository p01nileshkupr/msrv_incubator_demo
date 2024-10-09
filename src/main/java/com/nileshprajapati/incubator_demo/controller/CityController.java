package com.nileshprajapati.incubator_demo.controller;

import com.nileshprajapati.incubator_demo.model.City;
import com.nileshprajapati.incubator_demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(path = "/api/v1/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(path = "/getCities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> getCityByCountry(@RequestParam String countryID,
                                                     @RequestParam int limit,
                                                     @RequestParam int offset) {
        try {
            Future<List<City>> futureResults = this.cityService.getAllCities(countryID, limit, offset);
            return futureResults.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
