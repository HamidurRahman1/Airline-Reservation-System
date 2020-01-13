package com.hamidur.RESTfulSpringBootMicroservice.rest;

import com.hamidur.RESTfulSpringBootMicroservice.models.Destination;
import com.hamidur.RESTfulSpringBootMicroservice.models.Flight;
import com.hamidur.RESTfulSpringBootMicroservice.models.Source;
import com.hamidur.RESTfulSpringBootMicroservice.repos.DestinationRepository;
import com.hamidur.RESTfulSpringBootMicroservice.repos.FlightRepository;
import com.hamidur.RESTfulSpringBootMicroservice.repos.SourceRepository;
import com.hamidur.RESTfulSpringBootMicroservice.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

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
    public ResponseEntity<Flight> insertFlight(@RequestBody Flight flight)
    {
        Source source = sourceRepository.save(flight.getSource());
        Destination destination = destinationRepository.save(flight.getDestination());
        flight.setSource(source);
        flight.setDestination(destination);
        Flight f = flightRepository.save(flight);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    @GetMapping(value = "/flight/{flightId}")
    public ResponseEntity<Flight> get(@PathVariable Integer flightId)
    {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        return optionalFlight.isPresent() ? new ResponseEntity<>(optionalFlight.get(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlights()
    {
        Iterable<Flight> iterable = flightRepository.findAll();
        if(iterable != null)
        {
            Set<Flight> flights = new LinkedHashSet<>();
            iterable.forEach(flight -> flights.add(flight));
            if(flights.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/flights/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlightsByToday()
    {
        Iterable<Flight> iterable = flightRepository.findByCurrentDateTime(Util.toDBDateTime(LocalDateTime.now()));
        if(iterable == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            Set<Flight> flights = new LinkedHashSet<>();
            iterable.forEach(flight -> flights.add(flight));
            if(flights.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/flightsByFare/{fare}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlightsByFare(@PathVariable Float fare)
    {
        Set<Flight> flights = flightRepository.findByFare(fare);
        return !flights.isEmpty() ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
