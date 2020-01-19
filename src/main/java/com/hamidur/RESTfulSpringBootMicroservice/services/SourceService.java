package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.repos.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService
{
    private final SourceRepository sourceRepository;

    @Autowired
    public SourceService(final SourceRepository sourceRepository)
    {
        this.sourceRepository = sourceRepository;
    }
}
