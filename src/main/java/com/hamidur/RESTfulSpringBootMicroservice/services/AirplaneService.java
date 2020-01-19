package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.repos.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneService
{
    private final AirplaneRepository airplaneRepository;

    @Autowired
    public AirplaneService(final AirplaneRepository airplaneRepository)
    {
        this.airplaneRepository = airplaneRepository;
    }
}
