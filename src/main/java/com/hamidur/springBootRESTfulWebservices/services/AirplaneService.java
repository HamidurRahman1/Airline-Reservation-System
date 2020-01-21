package com.hamidur.springBootRESTfulWebservices.services;

import com.hamidur.springBootRESTfulWebservices.models.Airplane;
import com.hamidur.springBootRESTfulWebservices.repos.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AirplaneService
{
    private final AirplaneRepository airplaneRepository;

    @Autowired
    public AirplaneService(final AirplaneRepository airplaneRepository)
    {
        this.airplaneRepository = airplaneRepository;
    }

    public List<Airplane> getAirplanes()
    {
        Iterable<Airplane> airplanes = airplaneRepository.findAll();
        if(airplanes != null)
        {
            List<Airplane> airplaneList = new LinkedList<>();
            airplanes.forEach(airplane -> airplaneList.add(airplane));
            return airplaneList;
        }
        return null;
    }
}
