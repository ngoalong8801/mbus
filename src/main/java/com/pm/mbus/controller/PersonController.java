package com.pm.mbus.controller;

import com.pm.mbus.common.response.Response;
import com.pm.mbus.common.response.ResponseBuilderImpl;
import com.pm.mbus.dto.payload.Person;
import com.pm.mbus.event.producer.PersonProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Say hi to another person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hi to person successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Access Failed",
                    content = @Content) })
    @PostMapping("")
    public ResponseEntity<Person> hiPerson(@RequestBody Person person) {
        producer.hiPerson(person);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(person);
    }
}
