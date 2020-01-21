package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.repos.DestinationRepository;
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
