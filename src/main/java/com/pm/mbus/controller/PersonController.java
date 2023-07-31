package com.pm.mbus.controller;

import com.pm.mbus.common.response.Response;
import com.pm.mbus.common.response.ResponseBuilderImpl;
import com.pm.mbus.dto.payload.Person;
import com.pm.mbus.event.producer.PersonProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    public static final Logger LOG = LogManager.getLogger(PersonController.class);
    @Autowired
    PersonProducer producer;
    @PostMapping("")
    public ResponseEntity<Person> hiPerson(@RequestBody Person person) {
        producer.hiPerson(person);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(person);
    }
}
