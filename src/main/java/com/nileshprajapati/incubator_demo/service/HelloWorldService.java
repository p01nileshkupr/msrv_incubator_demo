package com.nileshprajapati.incubator_demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class HelloWorldService {

    public String sayHello() {
        return "Hello World!";
    }

    @Async
    public Future<String> post(String message) {
        return new AsyncResult<>(message);
    }
}
