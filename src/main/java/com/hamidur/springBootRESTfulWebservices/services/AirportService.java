package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.models.Airport;
import com.hamidur.springBootRESTfulWebservices.repos.AirportRepository;
import com.hamidur.springBootRESTfulWebservices.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService
{
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(final AirportRepository airportRepository)
    {
        this.airportRepository = airportRepository;
    }

    public Airport addAirport(Airport airport) throws IllegalArgumentException, NullPointerException
    {
        if(Util.validateAirport(airport) && airport.getAirportId() != null && airport.getAirportId() > 0)
        {
            Optional<Airport> optional = airportRepository.findById(airport.getAirportId());
            if(optional.isPresent()) throw new IllegalArgumentException("An airport already exist with id="+airport.getAirportId());
        }
        return airportRepository.save(airport);
    }

    public List<Airport> getAirports()
    {
        Iterable<Airport> airports = airportRepository.findAll();
        if(airports != null)
        {
            List<Airport> airportList = new LinkedList<>();
            airports.forEach(airport -> airportList.add(airport));
            return airportList;
        }
        return null;
    }

    public Airport getAirportByName(String airportName)
    {
        if(airportName == null || airportName.trim().toLowerCase().isEmpty())
            throw new IllegalArgumentException("Invalid input for airport name.");
        return airportRepository.findByAirportNameIgnoreCase(airportName);
    }
}
