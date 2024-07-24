package com.nileshprajapati.incubator_demo.model;

public class ResponseModel {
    private String output;

    public ResponseModel(String message) {
        this.output = message;
    }

    public String getMessage() {
        return output;
    }
}

