package com.pm.mbus.controller;

import com.pm.mbus.common.response.Response;
import com.pm.mbus.common.response.ResponseBuilderImpl;
import com.pm.mbus.event.producer.HelloProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    HelloProducer producer;
    @GetMapping("")
    public ResponseEntity<String> sayHi() {
        producer.sayHi();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Hello Success");
    }

}
