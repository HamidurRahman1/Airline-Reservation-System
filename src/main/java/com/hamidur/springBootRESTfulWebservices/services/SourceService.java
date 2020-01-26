package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.models.Source;
import com.hamidur.springBootRESTfulWebservices.repos.SourceRepository;
import com.hamidur.springBootRESTfulWebservices.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SourceService
{
    private final SourceRepository sourceRepository;

    @Autowired
    public SourceService(final SourceRepository sourceRepository)
    {
        this.sourceRepository = sourceRepository;
    }

    public Source getSourceById(Integer sourceId) throws IllegalArgumentException
    {
        if(Util.validateNumber(sourceId))
        {
            Optional<Source> source = sourceRepository.findById(sourceId);
            return source.isPresent() ? source.get() : null;
        }
        return null;
    }

    public Source addSource(Source source) throws IllegalArgumentException
    {
        if(Util.validateSource(source))
        {
            return sourceRepository.save(source);
        }
        return null;
    }
}
