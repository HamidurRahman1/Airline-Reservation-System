package com.hamidur.RESTfulSpringBootMicroservice.rest;

import com.hamidur.RESTfulSpringBootMicroservice.models.Flight;

import com.hamidur.RESTfulSpringBootMicroservice.repos.DestinationRepository;
import com.hamidur.RESTfulSpringBootMicroservice.repos.FlightRepository;
import com.hamidur.RESTfulSpringBootMicroservice.repos.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/p")
public class ProtectedRESTController
{
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private DestinationRepository destinationRepository;

    @PostMapping(value = "/insertFlight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertFlight(@RequestBody Flight flight)
    {
        System.out.println(flight);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
