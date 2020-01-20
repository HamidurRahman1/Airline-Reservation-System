package com.hamidur.RESTfulSpringBootMicroservice.rest;

import com.hamidur.RESTfulSpringBootMicroservice.errors.InvalidRequestException;
import com.hamidur.RESTfulSpringBootMicroservice.errors.InvalidRequestExceptionResponse;
import com.hamidur.RESTfulSpringBootMicroservice.models.Airline;
import com.hamidur.RESTfulSpringBootMicroservice.models.Airplane;
import com.hamidur.RESTfulSpringBootMicroservice.models.Airport;
import com.hamidur.RESTfulSpringBootMicroservice.models.Customer;
import com.hamidur.RESTfulSpringBootMicroservice.models.Flight;
import com.hamidur.RESTfulSpringBootMicroservice.models.Reservation;
import com.hamidur.RESTfulSpringBootMicroservice.services.AirlineService;
import com.hamidur.RESTfulSpringBootMicroservice.services.AirplaneService;
import com.hamidur.RESTfulSpringBootMicroservice.services.AirportService;
import com.hamidur.RESTfulSpringBootMicroservice.services.CustomerService;
import com.hamidur.RESTfulSpringBootMicroservice.services.DestinationService;
import com.hamidur.RESTfulSpringBootMicroservice.services.FlightService;
import com.hamidur.RESTfulSpringBootMicroservice.services.ReservationService;
import com.hamidur.RESTfulSpringBootMicroservice.services.SourceService;
import com.hamidur.RESTfulSpringBootMicroservice.utils.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/api/public")
public class RESTController
{
    private final AirportService airportService;
    private final AirlineService airlineService;
    private final AirplaneService airplaneService;
    private final CustomerService customerService;
    private final DestinationService destinationService;
    private final FlightService flightService;
    private final ReservationService reservationService;
    private final SourceService sourceService;

    @Autowired
    public RESTController(final AirportService airportService, final AirlineService airlineService,
                          final AirplaneService airplaneService, final CustomerService customerService,
                          final DestinationService destinationService, final FlightService flightService,
                          final ReservationService reservationService, final SourceService sourceService)
    {
        this.airportService = airportService;
        this.airlineService = airlineService;
        this.airplaneService = airplaneService;
        this.customerService = customerService;
        this.destinationService = destinationService;
        this.flightService = flightService;
        this.reservationService = reservationService;
        this.sourceService = sourceService;
    }

    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airport>> getAirports()
    {
        List<Airport> airports = airportService.getAirports();
        return airports != null ? new ResponseEntity<>(airports, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/airport/{airportName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airport> getAirportByName(@PathVariable String airportName)
    {
        try
        {
            Airport airport = null;
            if(Util.validateAirportName(airportName))
            {
                airport = airportService.getAirportByName(airportName);
            }
            return airport != null ? new ResponseEntity<>(airport, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airline>> getAirlines()
    {
        List<Airline> airlines = airlineService.getAirlines();
        return airlines != null ? new ResponseEntity<>(airlines, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/airline/{airlineName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airline> getAirlineByName(@PathVariable String airlineName)
    {
        try
        {
            Airline airline = null;
            if(Util.validateAirlineName(airlineName))
            {
                airline = airlineService.getAirlineByName(airlineName);
            }
            return airline != null ? new ResponseEntity<>(airline, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airline/{airlineName}/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Airplane>> getAirplanesByAirlineName(@PathVariable String airlineName)
    {
        try
        {
            Set<Airplane> airplanes = null;
            if(Util.validateAirlineName(airlineName)) airplanes = airlineService.getAirplanesByAirlineName(airlineName);
            return airplanes != null ? new ResponseEntity<>(airplanes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airplane>> getAirplanes()
    {
        List<Airplane> airplanes = airplaneService.getAirplanes();
        return airplanes != null ? new ResponseEntity<>(airplanes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Customer>> getCustomers()
    {
        Set<Customer> customers = customerService.getCustomers();
        return customers != null ? new ResponseEntity<>(customers, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/customer/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email)
    {
        Customer customer = customerService.getCustomerByEmail(email);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping(value = "/flight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Flight> insertFlight(@RequestBody Flight flight)
//    {
//        Source source = sourceRepository.save(flight.getSource());
//        Destination destination = destinationRepository.save(flight.getDestination());
//        flight.setSource(source);
//        flight.setDestination(destination);
//        Flight f = flightRepository.save(flight);
//        return new ResponseEntity<>(f, HttpStatus.OK);
//    }

    @GetMapping(value = "/flight/{flightId}")
    public ResponseEntity<Flight> get(@PathVariable Integer flightId)
    {
        try
        {
            Flight flight = null;
            if(Util.validateNumber(flightId)) flight = flightService.getFlightById(flightId);
            return flight != null ? new ResponseEntity<>(flight, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlights()
    {
        Set<Flight> flights = flightService.getFlights();
        return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/flights/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Flight>> getFlightsByToday()
    {
        Set<Flight> flights = flightService.getFlightsForToday();
        return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/flights/{date}")
    public ResponseEntity<Set<Flight>> getFlightsByDate(@PathVariable String date)
    {
        try
        {
            Set<Flight> flights = flightService.getFlightsByDate(Util.stringDateToDateTime(date));
            return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

//    @GetMapping(value = "/flightsByFare/{fare}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Set<Flight>> getFlightsByFare(@PathVariable Float fare)
//    {
//        Set<Flight> flights = flightRepository.findByFare(fare);
//        return !flights.isEmpty() ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @GetMapping(value = "/flightsByStatus/{status}")
    public ResponseEntity<Set<Flight>> getFlightsByStatus(@PathVariable String status)
    {
        try
        {
            Set<Flight> flights = flightService.getFlightsByStats(status);
            return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/rsvps/customer/{customerId}")
    public ResponseEntity<Set<Reservation>> getAllRSVPsByCustomerId(@PathVariable Integer customerId)
    {
        try
        {
            Set<Reservation> reservations = reservationService.getAllRSVPsByCustomerId(customerId);
            return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/rsvps/cancelled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getAllCancelledRSVPs()
    {
        Set<Reservation> reservations = reservationService.getAllCancelledRSVPs();
        return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/rsvps/{airline}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getAllActiveRSVPsByAirline(@PathVariable String airline)
    {
        try
        {
            Set<Reservation> reservations = reservationService.getAllActiveRSVPsByAirline(airline);
            return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/rsvps/{airline}/cancelled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getAllCancelledRSVPsByAirline(@PathVariable String airline)
    {
        try
        {
            Set<Reservation> reservations = reservationService.getAllCancelledRSVPsByAirline(airline);
            return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PostMapping(value = "/rsvp/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addRSVPByCustomerId(@RequestBody Map<String, Object> json)
    {
        try
        {
            return reservationService.addRSVPByCustomerId(json) ? new ResponseEntity<>(true, HttpStatus.OK) :
                    new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        catch (InvalidRequestException ex)
        {
            InvalidRequestExceptionResponse response = new InvalidRequestExceptionResponse(ex);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (NoSuchElementException ex)
        {
            InvalidRequestExceptionResponse response = new InvalidRequestExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/cancel/rsvp/{rsvpId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cancelRSVPByCustomerId(@PathVariable Integer rsvpId)
    {
        try
        {
            boolean isCancelled = reservationService.cancelRSVPByCustomerId(rsvpId);
            return isCancelled ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }
}
