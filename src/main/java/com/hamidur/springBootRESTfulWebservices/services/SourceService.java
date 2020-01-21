package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.repos.SourceRepository;
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
