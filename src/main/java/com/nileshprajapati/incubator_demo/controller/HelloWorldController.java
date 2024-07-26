package com.nileshprajapati.incubator_demo.controller;

import com.nileshprajapati.incubator_demo.model.InputRequest;
import com.nileshprajapati.incubator_demo.model.ResponseModel;
import com.nileshprajapati.incubator_demo.service.HelloWorldService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/greeting")
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    @Autowired
    HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping(
            path = "/greet",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseModel> helloWorld() {
        ResponseModel response = new ResponseModel("Hello World");
        return ResponseEntity.ok(response);
    }

    @PostMapping(
            path = "/post_message",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseModel> postMessage(@RequestBody InputRequest request) throws Exception {
        String input = request.getInputParam();

        if(input == null) {
            throw new BadRequestException("Request is empty and required parameter is missing.");
        }

        if(input.isEmpty()) {
            throw new BadRequestException("Required parameter is empty");
        }

        String message = this.helloWorldService.post(input);
        return ResponseEntity.ok(new ResponseModel(message));
    }
}
