package com.hamidur.RESTfulSpringBootMicroservice.rest;

import com.hamidur.RESTfulSpringBootMicroservice.errors.InvalidRequestException;
import com.hamidur.RESTfulSpringBootMicroservice.errors.InvalidRequestExceptionResponse;
import com.hamidur.RESTfulSpringBootMicroservice.models.Customer;
import com.hamidur.RESTfulSpringBootMicroservice.models.Destination;
import com.hamidur.RESTfulSpringBootMicroservice.models.Flight;
import com.hamidur.RESTfulSpringBootMicroservice.models.Reservation;
import com.hamidur.RESTfulSpringBootMicroservice.models.Source;
import com.hamidur.RESTfulSpringBootMicroservice.models.Status;
import com.hamidur.RESTfulSpringBootMicroservice.repos.CustomerRepository;
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
import java.util.Map;
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
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value = "/flight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/rsvps/customer/{customerId}")
    public ResponseEntity<Set<Reservation>> getAllRSVPsByCustomerId(@PathVariable Integer customerId)
    {
        Iterable<Reservation> iterable = reservationRepository.findAllRSVPByCustomerId(customerId);
        if(iterable != null)
        {
            Set<Reservation> reservations = new LinkedHashSet<>();
            iterable.forEach(reservation -> reservations.add(reservation));
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/rsvps/cancelled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getAllCancelledRSVPs()
    {
        Iterable<Reservation> iterable = reservationRepository.findReservationsByStatus(Status.CANCELLED);
        if(iterable != null)
        {
            Set<Reservation> reservations = new LinkedHashSet<>();
            iterable.forEach(reservation -> reservations.add(reservation));
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/rsvps/{airline}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getAllActiveRSVPsByAirline(@PathVariable String airline)
    {
        Iterable<Reservation> iterable = reservationRepository.findActiveReservationsByAirline(airline, Status.ACTIVE.toString());
        if(iterable != null)
        {
            Set<Reservation> reservations = new LinkedHashSet<>();
            iterable.forEach(reservation -> reservations.add(reservation));
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/rsvps/{airline}/cancelled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getAllCancelledRSVPsByAirline(@PathVariable String airline)
    {
        Iterable<Reservation> iterable = reservationRepository.findActiveReservationsByAirline(airline, Status.CANCELLED.toString());
        if(iterable != null)
        {
            Set<Reservation> reservations = new LinkedHashSet<>();
            iterable.forEach(reservation -> reservations.add(reservation));
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/rsvps/rsvp/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addRSVPForCustomerById(@RequestBody Map<String, Object> json)
    {
        int customerId = -1;
        int flightId = -1;
        try
        {
            if(Util.verifyRSVPByCustomerId(json))
            {
                customerId = (Integer) json.get(Util.CUSTOMER_ID_JKEY);
                Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
                if(!optionalCustomer.isPresent()) return new ResponseEntity<>("Customer does not exists with id="+customerId, HttpStatus.NOT_FOUND);
                Customer customer = optionalCustomer.get();

                flightId = (Integer)json.get(Util.FLIGHT_ID_JKEY);
                Optional<Flight> optionalFlight = flightRepository.findById(flightId);
                if(!optionalFlight.isPresent()) return new ResponseEntity<>("Flight does not exists with id="+flightId, HttpStatus.NOT_FOUND);
                Flight flight = optionalFlight.get();

                reservationRepository.insertRSVPByCustomerId(Util.toDBDateTime(LocalDateTime.now()), Status.ACTIVE.toString(), customerId, flightId);

                flight.getCustomers().add(customer);

                flightRepository.save(flight);
            }
        }
        catch (InvalidRequestException ex)
        {
            InvalidRequestExceptionResponse response = new InvalidRequestExceptionResponse(ex);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully reserved a seat for customer_id="+customerId+" in flight_id="+flightId, HttpStatus.OK);
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
