package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.models.Flight;
import com.hamidur.springBootRESTfulWebservices.models.Status;
import com.hamidur.springBootRESTfulWebservices.repos.FlightRepository;
import com.hamidur.springBootRESTfulWebservices.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FlightService
{
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(final FlightRepository flightRepository)
    {
        this.flightRepository = flightRepository;
    }

    public Flight addFlight(Flight flight)
    {
        if(Util.validateFlight(flight) && flight.getFlightId() != null && flight.getFlightId() > 0)
        {
            Optional<Flight> optional = flightRepository.findById(flight.getFlightId());
            if(optional.isPresent()) throw new IllegalArgumentException("A flight already exist with id="+flight.getFlightId());
        }
        flight.setStatus(Status.ON_TIME);
        return flightRepository.save(flight);
    }

    public Flight getFlightById(Integer flightId)
    {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        return optionalFlight.isPresent() ? optionalFlight.get() : null;
    }

    public Set<Flight> getFlights()
    {
        return iterableToSet(flightRepository.findAll());
    }

    public Set<Flight> getFlightsForToday()
    {
        LocalDateTime dateTime1 = Util.toDBDateTime(LocalDateTime.now());
        return iterableToSet(flightRepository.findByCurrentDateTime(dateTime1, dateTime1.plusHours(23)));
    }

    public Set<Flight> getFlightsByDate(LocalDateTime localDateTime)
    {
        return iterableToSet(flightRepository.findByDate(Util.toDBDateTime(localDateTime)));
    }

    public Set<Flight> getFlightsByStats(String status)
    {
        try
        {
            Status eStatus = Util.validateFlightStatus(status);
            return iterableToSet(flightRepository.findFlightsByStatus(eStatus));
        }
        catch (IllegalArgumentException ex)
        {
            throw ex;
        }
    }

    public Set<Flight> getFlightsByFare(Float fare)
    {
        if(Util.validateNumber(fare))
        {
            return flightRepository.findByFare(fare);
        }
        return null;
    }

    private Set<Flight> iterableToSet(Iterable<Flight> iterable)
    {
        if(iterable == null) return null;
        else
            {
            Set<Flight> flights = new LinkedHashSet<>();
            iterable.forEach(flight -> flights.add(flight));
            return flights;
        }
    }
}
