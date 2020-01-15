package com.hamidur.RESTfulSpringBootMicroservice.rest;

import com.hamidur.RESTfulSpringBootMicroservice.models.Destination;
import com.hamidur.RESTfulSpringBootMicroservice.models.Flight;
import com.hamidur.RESTfulSpringBootMicroservice.models.Reservation;
import com.hamidur.RESTfulSpringBootMicroservice.models.Source;
import com.hamidur.RESTfulSpringBootMicroservice.models.Status;
import com.hamidur.RESTfulSpringBootMicroservice.repos.DestinationRepository;
import com.hamidur.RESTfulSpringBootMicroservice.repos.FlightRepository;
import com.hamidur.RESTfulSpringBootMicroservice.repos.ReservationRepository;
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
    @Autowired
    private ReservationRepository reservationRepository;

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
        return iterableToSet(flightRepository.findAll());
    }

    @GetMapping(value = "/flights/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlightsByToday()
    {
        return iterableToSet(flightRepository.findByCurrentDateTime(Util.toDBDateTime(LocalDateTime.now())));
    }

    // date format: mm-dd-yyyy
    @GetMapping(value = "/flights/{date}")
    public ResponseEntity<Set<Flight>> getFlightsByDate(@PathVariable String date)
    {
        String[] parts = date.split("-");
        LocalDateTime requestedDate = LocalDateTime.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]), 0, 0, 1);

        return iterableToSet(flightRepository.findByDate(requestedDate));
    }

    @GetMapping(value = "/flightsByFare/{fare}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlightsByFare(@PathVariable Float fare)
    {
        Set<Flight> flights = flightRepository.findByFare(fare);
        return !flights.isEmpty() ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // See Status class for supported value
    @GetMapping(value = "/flightsByStatus/{status}")
    public ResponseEntity<Set<Flight>> getFlightsByStatus(@PathVariable Status status)
    {
        return iterableToSet(flightRepository.findFlightsByStatus(status));
    }

    private ResponseEntity<Set<Flight>> iterableToSet(Iterable<Flight> iterable)
    {
        if(iterable == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            Set<Flight> flights = new LinkedHashSet<>();
            iterable.forEach(flight -> flights.add(flight));
            if(flights.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
    }
}
