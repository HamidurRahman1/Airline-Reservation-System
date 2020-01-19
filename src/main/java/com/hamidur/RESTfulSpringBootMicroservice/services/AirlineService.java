package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.repos.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService
{
    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineService(final AirlineRepository airlineRepository)
    {
        this.airlineRepository = airlineRepository;
    }
}
