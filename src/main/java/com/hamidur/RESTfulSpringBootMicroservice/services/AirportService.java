package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.models.Airport;
import com.hamidur.RESTfulSpringBootMicroservice.repos.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AirportService
{
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(final AirportRepository airportRepository)
    {
        this.airportRepository = airportRepository;
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
