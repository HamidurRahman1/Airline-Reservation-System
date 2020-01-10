package com.hamidur.RESTfulSpringBootMicroservice.repos;

import com.hamidur.RESTfulSpringBootMicroservice.models.Airline;
import org.springframework.data.repository.CrudRepository;

public interface AirlineRepository extends CrudRepository<Airline, Integer>
{
    Airline findByAirlineNameIgnoreCase(String airlineName);
}
