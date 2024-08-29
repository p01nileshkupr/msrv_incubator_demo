package com.nileshprajapati.incubator_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IncubatorDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncubatorDemoApplication.class, args);
    }

}
