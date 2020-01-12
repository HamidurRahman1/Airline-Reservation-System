package com.hamidur.RESTfulSpringBootMicroservice.repos;

import com.hamidur.RESTfulSpringBootMicroservice.models.Destination;
import org.springframework.data.repository.CrudRepository;

public interface DestinationRepository extends CrudRepository<Destination, Integer> {
}
