package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService
{
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(final FlightRepository flightRepository)
    {
        this.flightRepository = flightRepository;
    }
}
