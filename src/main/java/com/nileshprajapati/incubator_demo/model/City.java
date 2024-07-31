package com.nileshprajapati.incubator_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class City {
    Long id;
    String wikiDataId;
    String type;
    String city;
    String name;
    String country;
    String countryCode;
    String region;
    String regionCode;
    String regionWdId;
    Float latitude;
    Float longitude;
    Long population;
}