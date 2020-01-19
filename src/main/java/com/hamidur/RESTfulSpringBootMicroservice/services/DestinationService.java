package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.repos.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationService
{
    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(final DestinationRepository destinationRepository)
    {
        this.destinationRepository = destinationRepository;
    }
}
