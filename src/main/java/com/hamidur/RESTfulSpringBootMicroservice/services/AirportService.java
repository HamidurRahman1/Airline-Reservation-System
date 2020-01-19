package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.repos.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService
{
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(final AirportRepository airportRepository)
    {
        this.airportRepository = airportRepository;
    }
}
