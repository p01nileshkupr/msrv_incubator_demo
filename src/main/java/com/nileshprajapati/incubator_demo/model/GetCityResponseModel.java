package com.nileshprajapati.incubator_demo.model;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@AllArgsConstructor
public class GetCityResponseModel {
    public List<City> data;
    public List<Map<String, Object>> links;
    public Map<String, Object> metadata;

    public List<City> getCities() {
        return data;
    }
}
