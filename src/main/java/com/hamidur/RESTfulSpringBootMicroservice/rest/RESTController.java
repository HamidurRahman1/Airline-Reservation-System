package com.hamidur.RESTfulSpringBootMicroservice.rest;

import com.hamidur.RESTfulSpringBootMicroservice.models.Airline;
import com.hamidur.RESTfulSpringBootMicroservice.models.Airplane;
import com.hamidur.RESTfulSpringBootMicroservice.models.Airport;
import com.hamidur.RESTfulSpringBootMicroservice.repos.AirlineRepository;
import com.hamidur.RESTfulSpringBootMicroservice.repos.AirplaneRepository;
import com.hamidur.RESTfulSpringBootMicroservice.repos.AirportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/public")
public class RESTController
{
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirplaneRepository airplaneRepository;

    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airport>> getAirports()
    {
        Iterable<Airport> airports = airportRepository.findAll();
        if(airports != null)
        {
            List<Airport> airportList = new LinkedList<>();
            airports.forEach(airport -> airportList.add(airport));
            return new ResponseEntity<>(airportList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/airlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airline>> getAirlines()
    {
        Iterable<Airline> airlines = airlineRepository.findAll();
        if(airlines != null)
        {
            List<Airline> airlineList = new LinkedList<>();
            airlines.forEach(airline -> airlineList.add(airline));
            return new ResponseEntity<>(airlineList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airplane>> getAirplanes()
    {
        Iterable<Airplane> airplanes = airplaneRepository.findAll();
        if(airplanes != null)
        {
            List<Airplane> airplaneList = new LinkedList<>();
            airplanes.forEach(airplane -> airplaneList.add(airplane));
            return new ResponseEntity<>(airplaneList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
