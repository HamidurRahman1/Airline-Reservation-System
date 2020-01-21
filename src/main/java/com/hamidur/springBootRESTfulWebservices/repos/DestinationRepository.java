package com.hamidur.springBootRESTfulWebservices.repos;

import com.hamidur.springBootRESTfulWebservices.models.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends CrudRepository<Destination, Integer> {
}
