package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.models.Airline;
import com.hamidur.RESTfulSpringBootMicroservice.models.Airplane;
import com.hamidur.RESTfulSpringBootMicroservice.repos.AirlineRepository;
import com.hamidur.RESTfulSpringBootMicroservice.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class AirlineService
{
    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineService(final AirlineRepository airlineRepository)
    {
        this.airlineRepository = airlineRepository;
    }

    public List<Airline> getAirlines()
    {
        Iterable<Airline> airlines = airlineRepository.findAll();
        if(airlines != null)
        {
            List<Airline> airlineList = new LinkedList<>();
            airlines.forEach(airline -> airlineList.add(airline));
            return airlineList;
        }
        return null;
    }

    public Airline getAirlineByName(String airlineName)
    {
        if(Util.validateAirlineName(airlineName))
            return airlineRepository.findByAirlineNameIgnoreCase(airlineName);
        return null;
    }

    public Set<Airplane> getAirplanesByAirlineName(String airlineName)
    {
        if(Util.validateAirlineName(airlineName))
        {
            Airline airline = airlineRepository.findByAirlineNameIgnoreCase(airlineName);
            if (airline != null) return airline.getAirplanes();
        }
        return null;
    }
}
