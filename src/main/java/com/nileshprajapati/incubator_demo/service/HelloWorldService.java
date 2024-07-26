package com.nileshprajapati.incubator_demo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String sayHello() {
        return "Hello World!";
    }

    public String post(String message) {
        return message;
    }
}
